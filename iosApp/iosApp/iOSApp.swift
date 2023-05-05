import SwiftUI
import core

@main
struct iOSApp: App {
    
    init() {
        KoinKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
