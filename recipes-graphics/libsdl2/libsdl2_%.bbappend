# To fix  "no accelerated colorspace conversion found" during ffplay, aka software decoding
PACKAGECONFIG:remove = "opengl"
