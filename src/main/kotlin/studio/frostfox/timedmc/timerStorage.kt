package studio.frostfox.timedmc

import net.minecraft.server.MinecraftServer
import net.minecraft.util.WorldSavePath
import java.nio.file.Files
import java.nio.file.StandardOpenOption.*

object TimerStorage {
    fun save(server: MinecraftServer, seconds: Long) {
        val dir = server.getSavePath(WorldSavePath.ROOT).resolve("timedmc")
        println("Saving timer to: $dir")
        Files.createDirectories(dir)

        val file = dir.resolve("timer.txt")
        Files.writeString(file, seconds.toString(), CREATE, TRUNCATE_EXISTING)
    }

    fun load(server: MinecraftServer): Long {
        val file = server.getSavePath(WorldSavePath.ROOT).resolve("timedmc/timer.txt")
        println("Loading timer from: $file")
        if (!Files.exists(file)) return 0L
        return Files.readString(file).toLongOrNull() ?: 0L
    }
}


