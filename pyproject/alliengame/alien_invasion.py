import pygame
from Settings import Settings
from Ship import Ship
import game_function as gf
from pygame.sprite import Group


def run_game():
    pygame.init()

    ai_settings = Settings()

    screen = pygame.display.set_mode((ai_settings.screen_width, ai_settings.screen_height))
    pygame.display.set_caption("louis_Invasion")
    # 创建一个飞船
    ship = Ship(ai_settings,screen)

    # 创建一个存储字段的编组
    bullets=Group()



    while True:
        gf.check_events(ai_settings,screen,ship,bullets)
        ship.update()
        gf.update_screen(ai_settings,screen,ship,bullets)



if __name__ == '__main__':
    run_game()




