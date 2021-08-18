package lt.ito.ito_commons

interface DeviceInfo {

    /**
     * @return device screen size
     */
    fun screenSize(): ScreenSize

    fun osVersion(): String

    fun sdkVersion(): Int

    fun model(): String

    fun manufacturer(): String

    //region Classes

    data class ScreenSize(
        val width: Int,
        val height: Int
    )

    //endregion
}