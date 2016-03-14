/*
 * FileName: User.java
 * Author:   v_qinyuchen
 * Date:     2016年3月14日 下午6:03:46
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.solr.demo.entity;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author v_qinyuchen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class User implements Serializable {

    /**
     */
    private static final long serialVersionUID = -7684929248461888873L;

    private int id;

    @Field
    private String name;

    @Field
    private int age;

    // 可以给某个属性重命名，likes就是solr index的属性
    // 在solrIndex中将显示like为likes
    @Field("likes")
    private String[] like;

    @Field
    private String address;

    @Field
    private String sex;

    @Field
    private String remark;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Field
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the like
     */
    public String[] getLike() {
        return like;
    }

    /**
     * @param like the like to set
     */
    public void setLike(String[] like) {
        this.like = like;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return this.id + "#" + this.name + "#" + this.age + "#" + this.like + "#" + this.address + "#" + this.sex + "#"
                + this.remark;
    }
}
