package com.country.countryside.pedigree.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.config.enums.ErrorCodeEnum;
import com.country.countryside.countryside.bean.TbProcess;
import com.country.countryside.countryside.mapper.TbProcessMapper;
import com.country.countryside.exception.DescribeException;
import com.country.countryside.pedigree.bean.TbPedigree;
import com.country.countryside.pedigree.bean.TbPedigreeTree;
import com.country.countryside.pedigree.mapper.TbPedigreeMapper;
import com.country.countryside.pedigree.mapper.TbPedigreeTreeMapper;
import com.country.countryside.pedigree.service.TbPedigreeService;
import com.country.countryside.pedigree.vo.PedigreeInVo;
import com.country.countryside.pedigree.vo.PedigreeTreeInVo;
import com.country.countryside.user.bean.TbUser;
import com.country.countryside.user.mapper.TbUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @Resource
    private TbUserMapper tbUserMapper;

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

    /**
     * 移除用户族谱信息，移除族谱信息会移除用户以及用户以下节点的信息
     * @param userId
     */
    @Override
    public void removePedigreeInfo(String userId) {

    }

    /**
     * 设置父节点接口
     * 判断子节点是否还有子节点，如果有则递归修改子节点层级信息
     * @param startUser
     * @param targetUser
     */
    @Transactional
    @Override
    public void extend(String startUser, String targetUser) {
        //判断人员信息是否存在，不存在则返回异常
        TbUser tbUser = tbUserMapper.findById(startUser);
        if(tbUser == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30005.getCode(),ErrorCodeEnum.ERROR_0xbdc30005.getTips());
        }
        TbUser _tbUser = tbUserMapper.findById(targetUser);
        if(_tbUser == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc30005.getCode(),ErrorCodeEnum.ERROR_0xbdc30005.getTips());
        }
        TbPedigreeTree tbPedigreeTree = tbPedigreeTreeMapper.findByUserId(targetUser);
        if(tbPedigreeTree == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc40001.getCode(),ErrorCodeEnum.ERROR_0xbdc40001.getTips());
        }
        recursion(startUser, tbPedigreeTree.getLayer(), tbPedigreeTree.getId());
    }

    /**
     * 修改族谱起始点信息
     * @param id
     * @param startIndex
     */
    @Transactional
    @Override
    public void updateStartIndex(String id, String startIndex) {
        TbPedigree tbPedigree = tbPedigreeMapper.findById(id);
        if(tbPedigree == null){
            throw new DescribeException(ErrorCodeEnum.ERROR_0xbdc40002.getCode(),ErrorCodeEnum.ERROR_0xbdc40002.getTips());
        }
        tbPedigreeMapper.updateStartIndex(id, startIndex);
    }

    /**
     * 递归修改节点层级数据
     * @param startUser
     * @param layer
     */
    private void recursion(String startUser, int layer, String parentId){
        TbPedigreeTree tbPedigreeTree = tbPedigreeTreeMapper.findByUserId(startUser);
        if(tbPedigreeTree == null){
            return;
        }
        tbPedigreeTree.setLayer(layer);
        if(!StringUtils.isEmpty(parentId)) {
            tbPedigreeTree.setParentId(parentId);
        }
        tbPedigreeTreeMapper.updatePedigreeTree(tbPedigreeTree);
        List<TbPedigreeTree> treeList = tbPedigreeTreeMapper.findByParentId(tbPedigreeTree.getId());
        if(treeList != null && treeList.size() > 0){
            treeList.forEach((tree) -> {
                recursion(tree.getUserId(), tree.getLayer() + 1, tree.getId());
            });
        }
    }
}
