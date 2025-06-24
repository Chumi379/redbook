package top.chumi.redbook.auth.domain.mapper;

import top.chumi.redbook.auth.domain.dataobject.UserDO;

/**
 * @author MingHu
 * @Date 2025/6/24 20:11
 * @Description: TODO
 */
public interface UserDOMapper {

    /**
     * 根据主键 ID 查询
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(UserDO record);

    /**
     * 根据主键 ID 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键 ID 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDO record);
}
