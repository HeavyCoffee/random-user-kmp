import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        DI.shared.start()
    }

    var body: some Scene {
        WindowGroup {
             ContentView()
        }
    }
}