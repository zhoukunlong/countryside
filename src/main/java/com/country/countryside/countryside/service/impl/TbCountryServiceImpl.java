package com.country.countryside.countryside.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.countryside.bean.TbCountry;
import com.country.countryside.countryside.mapper.TbCountryMapper;
import com.country.countryside.countryside.service.TbCountryService;
import com.country.countryside.countryside.vo.CountryInVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 村庄业务操作类
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
@Service
public class TbCountryServiceImpl implements TbCountryService {

    @Resource
    private TbCountryMapper tbCountryMapper;

    /**
     * 添加村庄
     * @param inVo
     */
    @Override
    public void addCountry(CountryInVo inVo) {
        try {
            TbCountry tbCountry = new TbCountry();
            BeanUtils.copyProperties(inVo,tbCountry);
            tbCountry.setCreateTime(CommonConstants.format.format(new Date()));
            tbCountry.setUpdateTime(CommonConstants.format.format(new Date()));
            tbCountry.setIsDelete(CommonConstants.Delete.NO);
            tbCountryMapper.addCountry(tbCountry);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
