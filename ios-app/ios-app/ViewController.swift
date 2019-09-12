//
//  ViewController.swift
//  ios-app
//
//  Created by Aleksey Mikhailov on 05/09/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import UIKit
import MultiPlatformLibrary
import MultiPlatformLibraryMvvm

class ViewController: UIViewController {

  @IBOutlet private var textLabel: UILabel!
  
  private var viewModel: TestViewModel!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
    
    let eventsDispatcher = EventsDispatcher<TestViewModelEventsListener>(listener: self)
    viewModel = TestViewModel(eventsDispatcher: eventsDispatcher)
    
    textLabel.bindText(liveData: viewModel.counter)
  }
  
  @IBAction func onUpButtonPressed() {
    viewModel.onUpButtonPressed()
  }
}

extension ViewController: TestViewModelEventsListener {
  func showAlert(text: StringDesc) {
    let alert = UIAlertController(title: nil,
                                  message: text.localized(),
                                  preferredStyle: .alert)
    alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
    
    present(alert, animated: true, completion: nil)
  }
}
