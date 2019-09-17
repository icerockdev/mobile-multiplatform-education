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
  
  @IBOutlet private var costRubInput: UITextField!
  @IBOutlet private var costDollarInput: UITextField!
  @IBOutlet private var nameLabel: UILabel!
  
  private var viewModel: TestViewModel!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
    
    let eventsDispatcher = EventsDispatcher<TestViewModelEventsListener>(listener: self)
    viewModel = TestViewModel(eventsDispatcher: eventsDispatcher)
    
    textLabel.bindText(liveData: viewModel.counter)
    
    costRubInput.bindTextTwoWay(liveData: viewModel.costRub)
    costRubInput.bindFocus(liveData: viewModel.costRubFocused)
    
    costDollarInput.bindTextTwoWay(liveData: viewModel.costDollar)
    costDollarInput.bindFocus(liveData: viewModel.costDollarFocused)
    
    nameLabel.bindText(liveData: viewModel.name)
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
