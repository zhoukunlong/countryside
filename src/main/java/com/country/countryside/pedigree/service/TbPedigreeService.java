package com.country.countryside.pedigree.service;

import com.country.countryside.pedigree.vo.NodeOutVo;
import com.country.countryside.pedigree.vo.PedigreeInVo;
import com.country.countryside.pedigree.vo.PedigreeTreeInVo;

import java.util.List;

/**
 *
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
public interface TbPedigreeService {

    /**
     * 添加族谱信息
     * @param inVo
     */
    void addPedigree(PedigreeInVo inVo);

    /**
     * 删除族谱信息
     * @param id
     */
    void deletePedigree(String id);

    /**
     * 添加树节点
     * @param inVo
     */
    void addPedigreeTree(PedigreeTreeInVo inVo);

    /**
     * 修改节点信息
     * @param inVo
     */
    void updatePedigreeTree(PedigreeTreeInVo inVo);

    /**
     * 申请创建族谱
     * @param userId
     * @param name
     * @return
     */
    String applyPedigree(String userId, String name, String countryId);

    /**
     * 设置父节点
     * @param startUser
     * @param targetUser
     */
    void extend(String startUser, String targetUser);

    /**
     * 修改族谱起始点
     * @param id
     * @param startIndex
     */
    void updateStartIndex(String id, String startIndex);

    /**
     * 根据父节点id查询子节点信息
     * @param id
     * @return
     */
    List<NodeOutVo> findByParentId(String id);

    /**
     * 根据id查询节点信息
     * @param id
     * @return
     */
    NodeOutVo findById(String id);

    /**
     * 查询所有同代人
     * @param userId
     * @return
     */
    List<NodeOutVo> findCogenerations(String userId);
}
