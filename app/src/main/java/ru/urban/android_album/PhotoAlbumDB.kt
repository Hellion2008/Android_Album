package ru.urban.android_album

class PhotoAlbumDB {

    private val _photos: List<Int> = listOf(
        R.drawable.pic1,
        R.drawable.pic2,
        R.drawable.pic3,
        R.drawable.pic4,
        R.drawable.pic5
    )

    val photos: List<Int>
        get() = _photos
}