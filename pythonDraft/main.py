from PIL import Image

resH = 100


def new_img(r=255, g=255, b=255):
    img = Image.new('RGB', (resH, 100))
    ph, pv = 0, 0
    while ph <= resH/4:
        img.putpixel((ph, pv), (r, g, b))
        ph += 1
        if ph == resH/4:
            ph, pv = 0, pv + 1
        if pv == 100:
            break

    img.save('sqr.png')

    return img


r = int(int(input("Input R: ")) * 2.55)
g = int(int(input("Input G: ")) * 2.55)
b = int(int(input("Input B: ")) * 2.55)
wallpaper = new_img(r, g, b)
wallpaper.show()
