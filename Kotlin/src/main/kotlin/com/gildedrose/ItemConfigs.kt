package com.gildedrose

object ItemConfigs {

    /**
     * A list of [item configurations][ItemConfig] describing how items of different
     * types, matched by name, should be updated. The last item in the
     * list should be a match-all config.
     */
    private val configs: List<ItemConfig> = listOf(
        ItemConfig {
            nameShouldMatch("Aged Brie")
            qualityShouldChangeBy(1)
        },
        ItemConfig {
            nameShouldMatch { it.startsWith("Backstage passes") }
            qualityShouldChangeBy { item ->
                when {
                    item.sellIn < 0 -> 0
                    item.sellIn < 5 -> item.quality + 3
                    item.sellIn < 10 -> item.quality + 2
                    else -> item.quality + 1
                }
            }
        },
        ItemConfig {
            nameShouldMatch("Sulfuras, Hand of Ragnaros")
            maxQuality = 80
            qualityShouldChangeBy(0)
            sellInShouldChangeBy(0)
        },
        ItemConfig { },
    )

    fun findItemConfigFor(item: Item): ItemConfig =
        configs.find { it.nameMatcher(item.name) }
            ?: throw IllegalStateException("No config found for ${item.name}")

}
