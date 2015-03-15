" Ctrl p to ignore .gradle, .git and build folders "
let g:ctrlp_custom_ignore = '\v[\/](\.gradle|\.git|build)$'

" Add some paths to syntastic java checker "
let g:syntastic_java_javac_classpath = "$ANDROID_HOME/platforms/android-22/*.jar:./build/intermediates/classes/debug:./build/intermediates/exploded-aar/**/*.jar"
