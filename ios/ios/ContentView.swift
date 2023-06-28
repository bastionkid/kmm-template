import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        VStack {
            Text(Greeting().greet())

            Text(StringRes().getString(id: SharedRes.strings().label_name, args: nil))
            
            Text(StringRes().getString(id: SharedRes.strings().label_name_with_args, args: ["Args"]))
            
            Text("Real Text")
                .foregroundColor(SharedRes.colors().red.getUIColor().toColor())
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

extension UIColor {
    func toColor() -> SwiftUI.Color {
        return Color(red: Double(cgColor.components![0]), green: Double(cgColor.components![1]), blue: Double(cgColor.components![2]))
    }
}
