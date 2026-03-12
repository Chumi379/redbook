package top.chumi.redbook.auth.runner;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import top.chumi.framework.common.util.JsonUtils;
import top.chumi.redbook.auth.constant.RedisKeyConstants;
import top.chumi.redbook.auth.domain.dataobject.PermissionDO;
import top.chumi.redbook.auth.domain.dataobject.RoleDO;
import top.chumi.redbook.auth.domain.dataobject.RolePermissionDO;
import top.chumi.redbook.auth.domain.mapper.PermissionDOMapper;
import top.chumi.redbook.auth.domain.mapper.RoleDOMapper;
import top.chumi.redbook.auth.domain.mapper.RolePermissionDOMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *  @author MingHu
 *  @Date 2026/3/12 14:14
 *  @Description: 推送角色权限数据到Redis
 */
@Slf4j
public class PushRolePermissions2RedisRunner implements ApplicationRunner {
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private RoleDOMapper roleDOMapper;
	@Resource
	private PermissionDOMapper permissionDOMapper;
	@Resource
	private RolePermissionDOMapper rolePermissionDOMapper;

	private static final String PUSH_PERMISSION_FLAG = "push.permission.flag";


	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("===> 服务启动，开始同步角色权限数据到Redis中...");
		try {
			List<RoleDO> roleDOS = roleDOMapper.selectEnabledList();
			if (CollUtil.isNotEmpty(roleDOS)) {
				Boolean canPushed = redisTemplate.opsForValue()
						.setIfAbsent(PUSH_PERMISSION_FLAG, "1", 1, TimeUnit.DAYS);

				if (Boolean.FALSE.equals(canPushed)) {
					log.warn("===> 角色权限数据已存在，请勿重复推送!!!");
					return;
				}

				// 查询所有角色ID
				List<Long> roleIds = roleDOS.stream().map(RoleDO::getId).toList();

				// 根据角色ID，批量查询出所有角色对应的权限
				List<RolePermissionDO> rolePermissionDOS = rolePermissionDOMapper.selectByRoleIds(roleIds);

				// 按角色ID分组，每个角色ID对应多个权限ID
				Map<Long, List<Long>> roleIdPermissionIdsMap = rolePermissionDOS.stream().collect(
						Collectors.groupingBy(RolePermissionDO::getRoleId,
								Collectors.mapping(RolePermissionDO::getPermissionId, Collectors.toList())));

				// 查询APP端所有被启动的权限
				List<PermissionDO> permissionDOS = permissionDOMapper.selectAppEnabledList();

				// 权限ID - 权限DO
				Map<Long, PermissionDO> permissionIdDOMap = permissionDOS.stream()
						.collect(Collectors.toMap(PermissionDO::getId, permissionDO -> permissionDO));

				// 组织角色ID-权限关系
				Map<Long, List<PermissionDO>> roleIdPermissionDOMap = Maps.newHashMap();

				roleDOS.forEach(roleDO -> {
					Long roleId = roleDO.getId();
					List<Long> permissionIds = roleIdPermissionIdsMap.get(roleId);
					if (CollUtil.isNotEmpty(permissionIds)) {
						List<PermissionDO> perDOS = Lists.newArrayList();

						permissionIds.forEach(permissionId -> {
							PermissionDO permissionDO = permissionIdDOMap.get(permissionId);
							if (Objects.nonNull((permissionDO))) {
								perDOS.add(permissionDO);
							}
						});
						roleIdPermissionDOMap.put(roleId, perDOS);
					}
				});

				roleIdPermissionDOMap.forEach((roleId, permissions) -> {
					String key = RedisKeyConstants.buildRolePermissionKey(roleId);
					redisTemplate.opsForValue().set(key, JsonUtils.toJsonString(permissionDOS));
				});

			}
			log.info("===> 成功同步角色权限数据到Redis中!!!");
		} catch (Exception e) {
			log.error("===> 同步角色权限数据到Redis失败: {}", e.getMessage(), e);
		}

	}
}
