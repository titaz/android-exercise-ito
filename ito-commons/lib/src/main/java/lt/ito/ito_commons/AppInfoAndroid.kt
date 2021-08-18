package lt.ito.ito_commons

class AppInfoAndroid() : AppInfo {

    override fun appId(): String {
        return BuildConfig.APPLICATION_ID
    }

    override fun version(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun productFlavour(): String {
        return BuildConfig.FLAVOR
    }

    override fun buildType(): String {
        return BuildConfig.BUILD_TYPE
    }

}