
import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryMvvm

class AuthViewController: UIViewController {
  
  @IBOutlet var loginTextField: UITextField!
  @IBOutlet var passwordTextField: UITextField!
  @IBOutlet var loginButton: UIButton!
  @IBOutlet var activityIndicator: UIActivityIndicatorView!
  
  private var viewModel: AuthViewModel!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    viewModel = AuthModuleKt.authModule
      .factory.createAuthViewModel(eventsDispatcher: EventsDispatcher(listener: self))
    
    loginTextField.bindTextTwoWay(liveData: viewModel.loginField.data)
    passwordTextField.bindTextTwoWay(liveData: viewModel.passwordField.data)
    loginButton.bindEnabled(liveData: viewModel.submitEnabled)
    activityIndicator.bindVisibility(liveData: viewModel.loading)
  }
  
  @IBAction func onLoginPressed(_ sender: Any) {
    viewModel.onSubmitButtonPressed()
  }
  
  deinit {
    viewModel.onCleared()
  }
}

extension AuthViewController: AuthViewModelEventsListener {
  func showError(message: StringDesc) {
    print(message.localized())
  }
  
  func routeToCompleteAuth() {
    print("route to complete!")
  }
}
