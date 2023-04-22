package dev.shushant.permission.player

interface MediaPlayerListener {
    fun onReady()
    fun onVideoCompleted()
    // TODO HIGH add onPlay, onPause events
}