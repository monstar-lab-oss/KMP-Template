import SwiftUI

@main
struct iOSApp: App {
    init() { Koin.start() }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
