package com.oleg.fashionclothes.network.module

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by oleg on 14.03.2018.
 */
@Root(name = "yml_catalog",strict = false)
class Catalog{
    @field:Element var shop: Shop?=null
}
@Root(name = "shop")
class Shop{
    @field:Element var offers: Offers? = null
}

@Root(name = "offers")
class Offers{
    @field:ElementList(inline = true) var offer: List<Offer>? = null
}

@Root(name = "offer")
class Offer{
    @field:Attribute(name = "id") var id:String? = null
    @field:Attribute(name = "type") var type:String? = null
    @field:Attribute(name = "available") var available: String? = null
    @field:Attribute(name = "selling_type") var selling_type: String? = null

    @field:Element(name = "name") var name:String? = null
    @field:Element(name = "description",required = false) var description: String? = ""
    @field:Element(name = "currencyId")var currencyId: String? = null
    @field:Element(name = "categoryId")var categoryId: String? = null
    @field:Element(name = "price")var price: String? = null
    @field:ElementList(name = "picture",entry = "picture",required = false,inline = true )var pictures: List<String>? = null
    @field:Element(name = "model")var model: String? = null
    @field:Element(name = "color")var color: String? = null
    @field:ElementList(inline = true) var param: List<Param>? = null
}

@Root(name = "param")
class Param{
    @field:Attribute(name = "name") var name:String? = null
}





