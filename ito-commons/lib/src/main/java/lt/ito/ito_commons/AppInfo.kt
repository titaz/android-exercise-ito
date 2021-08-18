package lt.ito.ito_commons

interface AppInfo {

    fun appId(): String

    fun version(): String

    fun productFlavour(): String

    fun buildType(): String
}