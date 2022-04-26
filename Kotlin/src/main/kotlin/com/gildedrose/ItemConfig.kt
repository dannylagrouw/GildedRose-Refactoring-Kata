package com.gildedrose

/**
 * Configuration for a type of [Item], describing how the
 * item's quality and sell-in-period will be updated. The
 * configuration contains default properties and behaviors
 * that can be overridden by the `config` parameter.
 */
class ItemConfig(config: (ItemConfig.() -> Unit)) {

    /**
     * Checks if a name matches for this config.
     */
    var nameMatcher: (String) -> Boolean = { true }

    /**
     * Updates an item's quality.
     */
    var updateQuality: (Item) -> Int = boundedUpdateQuality { it.quality + sellInQualityMultiplied(it) }

    /**
     * Updates an item's sell-in-period.
     */
    var updateSellIn: (Item) -> Int = { it.sellIn + sellInUpdateAmount }

    var maxQuality = 50

    private var qualityUpdateAmount = -1

    private var sellInUpdateAmount = -1

    private var sellInQualityMultiplier = 2

    init {
        config()
    }

    /**
     * Indicates this config is meant for items named [name].
     */
    fun nameShouldMatch(name: String) {
        nameMatcher = { it == name }
    }

    /**
     * Indicates this config is meant for items whose name matches
     * in [nameMatcher].
     */
    fun nameShouldMatch(nameMatcher: (String) -> Boolean) {
        this.nameMatcher = nameMatcher
    }

    /**
     * Indicates an item's quality should increase by [qualityUpdateAmount]
     * daily (or decrease if negative). The quality will always be kept
     * within its bounds (between 0 and [maxQuality]).
     */
    fun qualityShouldChangeBy(qualityUpdateAmount: Int) {
        this.qualityUpdateAmount = qualityUpdateAmount
    }

    /**
     * Sets a custom function to calculate an item's daily quality increment.
     * Regardless of the function's outcome, the quality will always be kept
     * within its bounds (between 0 and [maxQuality]).
     */
    fun qualityShouldChangeBy(updateQuality: (Item) -> Int) {
        this.updateQuality = boundedUpdateQuality(updateQuality)
    }

    /**
     * Indicates the amount by which an item's sell-in-period will change
     * each day.
     */
    fun sellInShouldChangeBy(sellInUpdateAmount: Int) {
        this.sellInUpdateAmount = sellInUpdateAmount
    }

    private fun sellInQualityMultiplied(item: Item) =
        if (item.sellIn < 0)
            qualityUpdateAmount * sellInQualityMultiplier
        else
            qualityUpdateAmount

    private fun boundedUpdateQuality(unboundedUpdateQuality: (Item) -> Int): (Item) -> Int {
        return {
            unboundedUpdateQuality(it).coerceIn(0, maxQuality)
        }
    }
}
