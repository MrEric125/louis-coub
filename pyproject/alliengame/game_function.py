import sys

import pygame

from alliengame.bullet import Bullet


def check_events(ai_settings,screen,ship,bullets):
    # 监视键盘和鼠标事件
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        elif event.type==pygame.KEYDOWN:
            check_keydown_events(event,ai_settings,screen,ship,bullets)

            if event.key == pygame.K_RIGHT:
                ship.moving_right=True
            if event.key == pygame.K_LEFT:
                ship.moving_left=True

        elif event.type==pygame.KEYUP:
            if event.key == pygame.K_RIGHT:
                ship.moving_right=False
            if event.key == pygame.K_LEFT:
                ship.moving_left=False


def update_screen(ai_settings,screen,ship,bullets):
    """ 更新屏幕上的图像 并切换到新屏幕中去"""
    #     让最近的绘制的屏幕可见
    screen.fill(ai_settings.bg_color)
    ship.blitme()

    # 让最近绘制的屏幕可见
    pygame.display.flip()


def check_keydown_events(event,ai_settings,screen,ship,bullets):
    if event.key==pygame.K_SPACE:
        new_bullet=Bullet(ai_settings,screen,ship)
        bullets.add(new_bullet)
