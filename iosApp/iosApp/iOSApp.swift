import SwiftUI
import OSLog
import Pulse
import PulseProxy
import PulseUI
import Shared

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil,
    ) -> Bool {
        
        NetworkLogger.enableProxy()
        PulseLoggerBridgeProvider.shared.bridge = PulseLoggerImpl()

        return true
    }
}

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
