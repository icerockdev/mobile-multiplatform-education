//
//  ViewController.swift
//  ios-app
//
//  Created by Aleksey Mikhailov on 05/09/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class ViewController: UIViewController {

  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
    
    HelloWorld().print()
    IosHelloWorld().print()
    
    print("start time \(CurrentTimeKt.getCurrentMilliSeconds()) milliseconds")
    
    HelloWorld().start()
  }
}

