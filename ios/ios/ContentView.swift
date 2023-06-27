import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        VStack {
            Text(Greeting().greet())

            Text(StringRes().getString(id: SharedRes.strings().label_name, args: nil))
            
            Text(StringRes().getString(id: SharedRes.strings().label_name_with_args, args: ["Args"]))
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
