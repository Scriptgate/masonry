#!/usr/bin/env bash

## script for renaming all java files:
#for i in $(find . -iname "*.java"); do
#	# cut -d '.' -f 1 -> split on . and only return the first field
#	export newname = echo $i | cut -d '.' -f 1
#    git mv -n "$i" "$(newname).groovy";
#done

# rename java directory to groovy
git mv src/main/java/net/ src/main/groovy/net
# change file extensions to .groovy
git mv src/main/groovy/net/scriptgate/masonry/api/Brick.java src/main/groovy/net/scriptgate/masonry/api/Brick.groovy
git mv src/main/groovy/net/scriptgate/masonry/api/BrickContainer.java src/main/groovy/net/scriptgate/masonry/api/BrickContainer.groovy
git mv src/main/groovy/net/scriptgate/masonry/api/Masonry.java src/main/groovy/net/scriptgate/masonry/api/Masonry.groovy
git mv src/main/groovy/net/scriptgate/masonry/api/Transition.java src/main/groovy/net/scriptgate/masonry/api/Transition.groovy
git mv src/main/groovy/net/scriptgate/masonry/BasicBrick.java src/main/groovy/net/scriptgate/masonry/BasicBrick.groovy
git mv src/main/groovy/net/scriptgate/masonry/BasicMasonry.java src/main/groovy/net/scriptgate/masonry/BasicMasonry.groovy
git mv src/main/groovy/net/scriptgate/masonry/BrickListContainer.java src/main/groovy/net/scriptgate/masonry/BrickListContainer.groovy
git mv src/main/groovy/net/scriptgate/masonry/LayoutPosition.java src/main/groovy/net/scriptgate/masonry/LayoutPosition.groovy
git mv src/main/groovy/net/scriptgate/masonry/Outlayer.java src/main/groovy/net/scriptgate/masonry/Outlayer.groovy
git mv src/main/groovy/net/scriptgate/masonry/transition/ArcedTransition.java src/main/groovy/net/scriptgate/masonry/transition/ArcedTransition.groovy
git mv src/main/groovy/net/scriptgate/masonry/transition/LinearTransition.java src/main/groovy/net/scriptgate/masonry/transition/LinearTransition.groovy
git mv src/main/groovy/net/scriptgate/masonry/transition/TransitionBase.java src/main/groovy/net/scriptgate/masonry/transition/TransitionBase.groovy
git mv src/main/groovy/net/scriptgate/masonry/transition/TransitionComponent.java src/main/groovy/net/scriptgate/masonry/transition/TransitionComponent.groovy
git mv src/main/groovy/net/scriptgate/masonry/utils/ArrayUtils.java src/main/groovy/net/scriptgate/masonry/utils/ArrayUtils.groovy
git mv src/test/groovy/net/scriptgate/helper/GifHelper.java src/test/groovy/net/scriptgate/helper/GifHelper.groovy
git mv src/test/groovy/net/scriptgate/helper/GifSequenceWriter.java src/test/groovy/net/scriptgate/helper/GifSequenceWriter.groovy
git mv src/test/groovy/net/scriptgate/masonry/demo/ColorBrick.java src/test/groovy/net/scriptgate/masonry/demo/ColorBrick.groovy
git mv src/test/groovy/net/scriptgate/masonry/demo/MasonryGui.java src/test/groovy/net/scriptgate/masonry/demo/MasonryGui.groovy
git mv src/test/groovy/net/scriptgate/masonry/gui/ColorBrickTest.java src/test/groovy/net/scriptgate/masonry/gui/ColorBrickTest.groovy
git mv src/test/groovy/net/scriptgate/masonry/gui/ExportTransition.java src/test/groovy/net/scriptgate/masonry/gui/ExportTransition.groovy
git mv src/test/groovy/net/scriptgate/masonry/Launcher.java src/test/groovy/net/scriptgate/masonry/Launcher.groovy
git mv src/test/groovy/net/scriptgate/masonry/transition/TransitionsTest.java src/test/groovy/net/scriptgate/masonry/transition/TransitionsTest.groovy
git mv src/test/groovy/net/scriptgate/masonry/transition/TransitionTrace.java src/test/groovy/net/scriptgate/masonry/transition/TransitionTrace.groovy