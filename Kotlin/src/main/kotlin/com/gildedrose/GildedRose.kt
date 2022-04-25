package com.gildedrose

class ItemConfig(config: (ItemConfig.() -> Unit)) {

    var nameMatcher: (String) -> Boolean = { true }

    var maxQuality = 50

    var qualityChangeParam = -1

    var sellInChangeParam = -1

    private var sellInQualityChangeModifier = 2

    var qualityChanger: (Item) -> Int = boundedQualityChange { it.quality + sellInQualityChange(it) }

    var sellInChanger: (Item) -> Int = { it.sellIn + sellInChangeParam }

    init {
        config()
    }

//    override fun toString(): String {
//        val name = when {
//            nameMatcher("Aged Brie") -> "Aged Brie"
//            nameMatcher("Backstage passes") -> "Backstage passes"
//            nameMatcher("Sulfuras, Hand of Ragnaros") -> "Sulfuras, Hand of Ragnaros"
//            else -> "Other"
//        }
//        return "Config(name = $name, maxQuality = $maxQuality, qualityChangeParam = $qualityChangeParam)"
//    }
//
    fun matchName(name: String) {
        nameMatcher = { it == name }
    }

    fun matchName(nameMatcher: (String) -> Boolean) {
        this.nameMatcher = nameMatcher
    }

    fun qualityChange(qualityChangeParam: Int) {
        this.qualityChangeParam = qualityChangeParam
    }

    fun qualityChange(qualityChange: (Item) -> Int) {
        this.qualityChanger = boundedQualityChange(qualityChange)
    }

    private fun sellInQualityChange(item: Item) =
        if (item.sellIn < 0)
            qualityChangeParam * sellInQualityChangeModifier
        else
            qualityChangeParam

    private fun boundedQualityChange(qualityChange: (Item) -> Int): (Item) -> Int {
        return {
            qualityChange(it).coerceIn(0, maxQuality)
        }
    }
}

val configs: List<ItemConfig> = listOf(
    ItemConfig {
        matchName("Aged Brie")
        qualityChange(1)
    },
    ItemConfig {
        matchName { it.startsWith("Backstage passes") }
    },
    ItemConfig {
        matchName("Sulfuras, Hand of Ragnaros")
        maxQuality = 80
        qualityChange(0)
        sellInChangeParam = 0
    },
    ItemConfig { },
)

class ItemConfigApplier(val item: Item) {
    val itemConfig = configs.find { it.nameMatcher(item.name) }
        ?: throw IllegalStateException("Can never happen")

    fun updatedItem(): Item =
        item.apply {
            item.sellIn = itemConfig.sellInChanger(item)
            item.quality = itemConfig.qualityChanger(item)
        }
}

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val itemConfig = ItemConfigApplier(items[i])
            items[i] = itemConfig.updatedItem()
            if (false) {
                if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        if (items[i].quality > 0) {
                            items[i].quality = items[i].quality - 1
                        }
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1

                        if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                            if (items[i].sellIn < 11) {
                                if (items[i].quality < 50) {
                                    items[i].quality = items[i].quality + 1
                                }
                            }

                            if (items[i].sellIn < 6) {
                                if (items[i].quality < 50) {
                                    items[i].quality = items[i].quality + 1
                                }
                            }
                        }
                    }
                }

                if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                    items[i].sellIn = items[i].sellIn - 1
                }

                if (items[i].sellIn < 0) {
                    if (items[i].name != "Aged Brie") {
                        if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                            if (items[i].quality > 0) {
                                if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                    items[i].quality = items[i].quality - 1
                                }
                            }
                        } else {
                            items[i].quality = items[i].quality - items[i].quality
                        }
                    } else {
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1
                        }
                    }
                }
            }
        }
    }

}

