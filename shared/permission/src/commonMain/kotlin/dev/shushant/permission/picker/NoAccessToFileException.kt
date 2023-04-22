package dev.shushant.permission.picker

class NoAccessToFileException(path: String) : RuntimeException("no access to $path")