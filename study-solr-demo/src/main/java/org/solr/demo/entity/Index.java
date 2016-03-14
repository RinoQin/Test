/*
 * FileName: Index.java
 * Author:   v_qinyuchen
 * Date:     2016年3月11日 下午3:30:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.solr.demo.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 〈一句话功能简述〉<br>
 * Index需要添加相关的注解，便于告诉solr哪些属性参加到index中
 * 
 * @author v_qinyuchen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Index {
    // @Field set方法上添加注解也是可以的
    private String id;

    @Field
    private String name;

    @Field
    private String manu;

    @Field
    private String[] cat;

    @Field
    private String[] features;

    @Field
    private float price;

    @Field
    private int popularity;

    @Field
    private boolean inStock;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Field
    public void setId(String id) {
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
     * @return the manu
     */
    public String getManu() {
        return manu;
    }

    /**
     * @param manu the manu to set
     */
    public void setManu(String manu) {
        this.manu = manu;
    }

    /**
     * @return the cat
     */
    public String[] getCat() {
        return cat;
    }

    /**
     * @param cat the cat to set
     */
    public void setCat(String[] cat) {
        this.cat = cat;
    }

    /**
     * @return the features
     */
    public String[] getFeatures() {
        return features;
    }

    /**
     * @param features the features to set
     */
    public void setFeatures(String[] features) {
        this.features = features;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * @param popularity the popularity to set
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * @return the inStock
     */
    public boolean isInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return this.id + "#" + this.name + "#" + this.manu + "#" + this.cat;
    }
}
