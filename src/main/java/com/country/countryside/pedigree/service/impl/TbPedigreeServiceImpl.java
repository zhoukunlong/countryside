package com.country.countryside.pedigree.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.countryside.bean.TbProcess;
import com.country.countryside.countryside.mapper.TbProcessMapper;
import com.country.countryside.pedigree.bean.TbPedigree;
import com.country.countryside.pedigree.bean.TbPedigreeTree;
import com.country.countryside.pedigree.mapper.TbPedigreeMapper;
import com.country.countryside.pedigree.mapper.TbPedigreeTreeMapper;
import com.country.countryside.pedigree.service.TbPedigreeService;
import com.country.countryside.pedigree.vo.PedigreeInVo;
import com.country.countryside.pedigree.vo.PedigreeTreeInVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 族谱信息业务类
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Service
public class TbPedigreeServiceImpl implements TbPedigreeService {

    @Resource
    private TbPedigreeMapper tbPedigreeMapper;
    @Resource
    private TbPedigreeTreeMapper tbPedigreeTreeMapper;
    @Resource
    private TbProcessMapper tbProcessMapper;

    /**
     * 添加族谱信息
     * @param inVo
     */
    @Transactional
    @Override
    public void addPedigree(PedigreeInVo inVo) {
        /**
         * 添加族谱信息
         */
        TbPedigree tbPedigree = new TbPedigree();
        BeanUtils.copyProperties(inVo,tbPedigree);
        tbPedigree.setIsDelete(CommonConstants.Delete.NO);
        tbPedigree.setCreateTime(CommonConstants.format.format(new Date()));
        tbPedigree.setUpdateTime(CommonConstants.format.format(new Date()));
        tbPedigreeMapper.addPedigree(tbPedigree);
        /**
         * 添加族谱信息的同时添加一个默认的族谱树源头
         */
        TbPedigreeTree tbPedigreeTree = new TbPedigreeTree();
        tbPedigreeTree.setCountryId(inVo.getCountryId());
        tbPedigreeTree.setCreateTime(CommonConstants.format.format(new Date()));
        tbPedigreeTree.setIsDelete(CommonConstants.Delete.NO);
        tbPedigreeTree.setLayer(1);
        tbPedigreeTree.setParentId("0");
        tbPedigreeTree.setPedigreeId(tbPedigree.getId());
        tbPedigreeTree.setWifeId("0");
        tbPedigreeTree.setUserId("0");
        tbPedigreeTree.setUpdateTime(CommonConstants.format.format(new Date()));
        tbPedigreeTreeMapper.addPedigreeTree(tbPedigreeTree);
    }

    /**
     * 删除族谱信息
     * @param id
     */
    @Override
    public void deletePedigree(String id) {

    }

    /**
     * 修改族谱信息
     * @param inVo
     */
    @Override
    public void updatePedigree(PedigreeInVo inVo) {

    }

    /**
     * 添加节点
     * @param inVo
     */
    @Override
    public void addPedigreeTree(PedigreeTreeInVo inVo) {

    }

    /**
     * 删除节点
     * @param id
     */
    @Override
    public void deletePedigreeTree(String id) {

    }

    /**
     * 修改节点
     * @param inVo
     */
    @Override
    public void updatePedigreeTree(PedigreeTreeInVo inVo) {

    }

    /**
     * 申请创建族谱
     * @param userId
     * @param name
     * @return
     */
    @Transactional
    @Override
    public String applyPedigree(String userId, String name, String countryId) {
        //添加申请信息
        TbProcess tbProcess = new TbProcess();
        tbProcess.setProcessTitle(CommonConstants.APPLY_PEDIGREE);
        tbProcess.setProcessContent(name);
        tbProcess.setUserId(userId);
        tbProcess.setIsDelete(CommonConstants.Delete.NO);
        tbProcess.setCreateTime(CommonConstants.format.format(new Date()));
        tbProcess.setUpdateTime(CommonConstants.format.format(new Date()));
        tbProcess.setStatus(CommonConstants.ProcessStatus.PROCESSING);
        tbProcess.setCountryId(countryId);
        tbProcessMapper.addProcess(tbProcess);
        //websocket推送消息到相应负责人
        return tbProcess.getId();
    }
}
