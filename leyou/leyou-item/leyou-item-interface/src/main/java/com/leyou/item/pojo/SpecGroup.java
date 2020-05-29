package com.leyou.item.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * @author ：胡锦洪
 * @date ：Created in 2020/3/5 20:25
 * 描述   ：
 */
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private String name;

    public SpecGroup() {
    }

    public SpecGroup(Long cid, String name) {
        this.cid = cid;
        this.name = name;
    }
    public SpecGroup(Long id,Long cid, String name) {
        this.id = id;
        this.cid = cid;
        this.name = name;
    }
    @Transient
    private List<SpecParam> params;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecParam> getParams() {
        return params;
    }

    public void setParams(List<SpecParam> params) {
        this.params = params;
    }
}

    