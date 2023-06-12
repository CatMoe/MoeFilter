package catmoe.fallencrystal.moefilter.api.logger

import catmoe.fallencrystal.moefilter.util.message.MessageUtil
import catmoe.fallencrystal.moefilter.util.plugin.FilterPlugin
import catmoe.fallencrystal.moefilter.util.plugin.util.Scheduler
import java.util.concurrent.CopyOnWriteArrayList
import java.util.logging.Filter
import java.util.logging.LogRecord

object LoggerManager : Filter {
    private val logger: MutableList<ILogger> = CopyOnWriteArrayList()

    private var logType: BCLogType? = null

    override fun isLoggable(record: LogRecord?): Boolean {
        var loggable = true
        // 如果ILogger为空时 默认true
        if (logger.isEmpty()) return true
        // logger.forEach { try { if (!it.isLoggable(record)) loggable = false } catch (ex: Exception) { MessageUtil.logWarnRaw("${it::class.java} throws an error. Ask that plugin developer using MoeFilter API") } }
        for (it in logger) {
            try {
                if (!it.isLoggable(record, loggable)) { loggable = false }
            } catch (ex: Exception) { MessageUtil.logWarnRaw("${it::class.java} throw an error. Ask that plugin developer what they using MoeFilter API.") }
        }
        return loggable
    }

    fun registerFilter(c: ILogger) { logger.add(c) }

    fun unregisterFilter(c: ILogger) {
        Scheduler(FilterPlugin.getPlugin()!!).runAsync {
            val removeLogger: MutableList<ILogger> = ArrayList()
            for (it in logger) { if (it::class.java == c::class.java) { removeLogger.add(it); break; } }
            logger.removeAll(removeLogger)
        }
    }

    fun getType(): BCLogType? { return logType }

    fun setType(logType: BCLogType) { this.logType=logType }
}