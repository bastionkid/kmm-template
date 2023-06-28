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
            
            Group {
                Text(StringRes().getPluralString(id: SharedRes.plurals().quantity, quantity: 0, args: nil))
                Text(StringRes().getPluralString(id: SharedRes.plurals().quantity, quantity: 1, args: nil))
                Text(StringRes().getPluralString(id: SharedRes.plurals().quantity, quantity: 2, args: nil))
            }
            
            Group {
                Text(StringRes().getPluralString(id: SharedRes.plurals().fruit_quantity, quantity: 0, args: [0, "Apple"]))
                Text(StringRes().getPluralString(id: SharedRes.plurals().fruit_quantity, quantity: 1, args: [1, "Apple"]))
                Text(StringRes().getPluralString(id: SharedRes.plurals().fruit_quantity, quantity: 2, args: [2, "Apple"]))
            }
            
            Image(uiImage: SharedRes.images().taj_mahal.toUIImage()!)
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
