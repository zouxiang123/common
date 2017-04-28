/**   
 * @Title: FamilyInitlalServiceImpl.java 
 * @Package com.xtt.txgl.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年4月18日 上午10:30:09 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.FamilyInitialCache;
import com.xtt.common.common.service.IFamilyInitialService;
import com.xtt.common.dao.mapper.FamilyInitialMapper;
import com.xtt.common.dao.model.FamilyInitial;
import com.xtt.common.dto.FamilyInitialDto;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.FamilyUtil;
import com.xtt.platform.util.lang.StringUtil;

/**
 * @ClassName: FamilyInitlalServiceImpl
 * @date: 2017年4月18日 上午10:30:09
 * @version: V1.0
 *
 */
@Service
public class FamilyInitialServiceImpl implements IFamilyInitialService {

    @Autowired
    private FamilyInitialMapper familyInitialMapper;

    /**
     * 根据条件查询
     */
    @Override
    public List<FamilyInitial> listByCondition(FamilyInitial record) {
        return familyInitialMapper.listByCondition(record);
    }

    @Override
    public void insert(FamilyInitial record) {
        if (!isChinese(record.getName())) {
            record.setName(record.getName().toUpperCase());
        }
        if (StringUtil.isBlank(record.getInitial())) {
            record.setInitial(FamilyUtil.getInitial(record.getName()).toUpperCase());
        }
        familyInitialMapper.insert(record);
        FamilyInitialDto cacheObj = new FamilyInitialDto();
        BeanUtil.copyProperties(record, cacheObj);
        FamilyInitialCache.refreshByFamily(cacheObj);
    }

    /**
     * 完整的判断中文汉字和符号
     * 
     * @Title: isChinese
     * @param name
     * @return
     *
     */
    public static boolean isChinese(String name) {
        char[] ch = name.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符是否为中文
     * 
     * @Title: isChinese
     * @param c
     * @return
     *
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    @Override
    public String getInitial(String familyName) {
        String initial = FamilyInitialCache.getInitialByFamily(familyName);
        // 缓存查不到则新增
        if (StringUtil.isBlank(initial)) {
            FamilyInitial obj = new FamilyInitial();
            obj.setName(familyName);
            insert(obj);
            return obj.getInitial().toUpperCase();
        } else {
            return initial.toUpperCase();
        }
    }

}
