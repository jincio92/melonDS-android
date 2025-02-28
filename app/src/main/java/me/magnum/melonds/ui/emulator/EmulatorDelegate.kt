package me.magnum.melonds.ui.emulator

import android.os.Bundle
import io.reactivex.Completable
import me.magnum.melonds.domain.model.EmulatorConfiguration

abstract class EmulatorDelegate(protected val activity: EmulatorActivity) {
    abstract fun getEmulatorSetupObservable(extras: Bundle?): Completable
    abstract fun getEmulatorConfiguration(): EmulatorConfiguration
    abstract fun getPauseMenuOptions(): List<PauseMenuOption>
    abstract fun onPauseMenuOptionSelected(option: PauseMenuOption)
    abstract fun performQuickSave()
    abstract fun performQuickLoad()
    abstract fun getCrashContext(): Any
    abstract fun dispose()
}