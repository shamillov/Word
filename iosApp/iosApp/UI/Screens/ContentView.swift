import SwiftUI
import core

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        TabView {
            CardsView()
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("Cards")
                }
            ExamView()
                .tabItem {
                    Image(systemName: "play.fill")
                    Text("Exam")
                }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
