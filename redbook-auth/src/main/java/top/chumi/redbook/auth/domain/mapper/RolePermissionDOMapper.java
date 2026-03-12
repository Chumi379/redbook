package top.chumi.redbook.auth.domain.mapper;

import org.apache.ibatis.annotations.Param;
import top.chumi.redbook.auth.domain.dataobject.RolePermissionDO;

import java.util.List;

public interface RolePermissionDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermissionDO record);

    int insertSelective(RolePermissionDO record);

    RolePermissionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermissionDO record);

    int updateByPrimaryKey(RolePermissionDO record);

	List<RolePermissionDO> selectByRoleIds(@Param("roleIds") List<Long> roleIds);


}
