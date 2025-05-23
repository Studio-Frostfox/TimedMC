package studio.frostfox.timedmc

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.server.MinecraftServer
import org.slf4j.LoggerFactory

object TimedMC : ModInitializer {
    private val logger = LoggerFactory.getLogger("timed-mc")

	override fun onInitialize() {
		ServerPlayConnectionEvents.JOIN.register { handler, sender, server ->
			Timer.startTimer(server)
			secondsInGame = TimerStorage.load(server)
		}

		ServerPlayConnectionEvents.DISCONNECT.register { handler, server ->
			TimerStorage.save(server, secondsInGame)
			secondsInGame = 0
		}
	}
}