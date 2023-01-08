package com.country.countryside.pedigree.service;

import com.country.countryside.pedigree.vo.PedigreeInVo;
import com.country.countryside.pedigree.vo.PedigreeTreeInVo;

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
     * 修改族谱信息
     * @param inVo
     */
    void updatePedigree(PedigreeInVo inVo);

    /**
     * 添加树节点
     * @param inVo
     */
    void addPedigreeTree(PedigreeTreeInVo inVo);

    /**
     * 删除节点
     * @param id
     */
    void deletePedigreeTree(String id);

    /**
     * 修改节点信息
     * @param inVo
     */
    void updatePedigreeTree(PedigreeTreeInVo inVo);

}
