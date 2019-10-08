Pod::Spec.new do |spec|
    spec.name                     = 'FeatureAuth'
    spec.version                  = '0.1.0'
    spec.homepage                 = 'Link to a Kotlin/Native module homepage'
    spec.source                   = { :git => "Not Published", :tag => "Cocoapods/#{spec.name}/#{spec.version}" }
    spec.authors                  = 'IceRock Development'
    spec.license                  = 'MIT'
    spec.summary                  = 'Shared code between iOS and Android'

    spec.module_name              = "#{spec.name}"
    spec.source_files             = "src/iosMain/swift/**/*.{h,m,swift}"
    spec.resources                = "src/iosMain/xib/**/*.{xib}"

    spec.dependency 'MultiPlatformLibrary'
    spec.dependency 'MultiPlatformLibraryResources'
    spec.dependency 'MultiPlatformLibraryMvvm/Core'
    
    spec.ios.deployment_target  = '11.0'
    spec.swift_version = '4.2'

    spec.pod_target_xcconfig = {
        'OTHER_LDFLAGS' => '-framework "MultiPlatformLibrary"',
        'VALID_ARCHS' => '$(ARCHS_STANDARD_64_BIT)'
    }
end
