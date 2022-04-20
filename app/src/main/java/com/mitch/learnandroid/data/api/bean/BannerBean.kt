package com.mitch.learnandroid.data.api.bean

/**
 * @Class: BannerBean
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/20
 */
data class BannerBean(
    var desc: String,
    var id: Int,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String


) {
    override fun toString(): String {
        return "BannerBean(desc='$desc', id=$id, imagePath='$imagePath', isVisible=$isVisible, order=$order, title='$title', type=$type, url='$url')"
    }
}