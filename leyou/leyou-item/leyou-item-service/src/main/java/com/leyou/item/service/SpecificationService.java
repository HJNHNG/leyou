package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：胡锦洪
 * @date ：Created in 2020/3/6 11:07
 * 描述   ：
 */
@Service
public class SpecificationService {
    @Autowired
    private SpecGroupMapper groupMapper;
    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 根据id查询参数组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return groupMapper.select(record);
    }

    /**
     * 根据id查询参数
     * @param gid
     * @return
     */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic,Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return paramMapper.select(record);
    }

    /**
     * 保存组
     * @param specGroup
     */
    @Transactional
    public void saveGroup(SpecGroup specGroup) {
        groupMapper.insert(specGroup);
    }

    /**
     * 修改组
     * @param specGroup
     */
    public void changeGroup(SpecGroup specGroup) {
        groupMapper.updateByPrimaryKey(specGroup);
    }

    /**
     * 删除组
     * @param id
     */
    public void deleteGroup(Long id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加参数
     * @param specParam
     */
    public void saveParam(SpecParam specParam) {
        paramMapper.insertSelective(specParam);
    }

    /**
     * 修改参数
     * @param specParam
     */
    public void changeParam(SpecParam specParam) {
        paramMapper.updateByPrimaryKey(specParam);
    }

    /**
     * 删除组
     * @param id
     */
    public void deleteParam(Long id) {
        paramMapper.deleteByPrimaryKey(id);
    }

    public List<SpecGroup> queryGroupsWithParam(Long cid) {
        List<SpecGroup> specGroups = this.queryGroupsByCid(cid);
        specGroups.forEach(specGroup -> {
            List<SpecParam> params = this.queryParams(specGroup.getId(), null, null, null);
            specGroup.setParams(params);
        });
        return specGroups;
    }
}

    