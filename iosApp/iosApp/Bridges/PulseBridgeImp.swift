import Foundation
import Pulse
import Shared

class PulseLoggerImpl: NSObject, PulseLoggerBridge {
    func log(message: String, tag: String) {
        LoggerStore.shared.storeMessage(label: tag, level: .info, message: message)
    }
}
