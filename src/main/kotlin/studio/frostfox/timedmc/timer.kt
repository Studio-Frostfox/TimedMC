package studio.frostfox.timedmc

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.server.MinecraftServer

var ticksInGame: Int = 0
var secondsInGame: Long = 0

private var started = false

object Timer {
    fun startTimer(server: MinecraftServer) {
        if (started) return  // prevent double registration
        started = true

        ServerTickEvents.START_SERVER_TICK.register { server ->
            ticksInGame++

            if (ticksInGame >= 20) {
                ticksInGame = 0
                secondsInGame++
                println("Seconds: $secondsInGame")
            }
        }
    }
}