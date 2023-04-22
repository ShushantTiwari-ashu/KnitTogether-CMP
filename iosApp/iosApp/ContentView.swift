import UIKit
import SwiftUI
import shared
import WebKit




typealias MyLambda = (String?) -> Void

let myLambda: MyLambda = { param in
    print("myLambda called")
    print(param ?? "nil")
    MyObservableObject.shared.url = param
}

struct ComposeView: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> UIViewController {
        AvoidDispose(Main_iosKt.MainViewController())
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @EnvironmentObject var globalObservable: MyObservableObject

    
    var body: some View {
        GeometryReader { geometry in
            VStack(spacing:0){
                ComposeView()
                    .ignoresSafeArea(.all) // Compose has own keyboard handler
                

            }
        }
    }
}

//struct CustomWebView : UIViewRepresentable {
//
//    let request: URLRequest
//
//    func makeUIView(context: Context) -> WKWebView  {
//        return myWebView.getWebView() as! WKWebView
//    }
//
//    func updateUIView(_ uiView: WKWebView, context: Context) {
//        uiView.load(request)
//    }
//
//}
//struct WebView_Previews : PreviewProvider {
//    static var previews: some View {
//        CustomWebView(request: URLRequest(url: URL(string: "https://www.apple.com")!))
//    }
//}
