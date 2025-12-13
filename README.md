# Mage Medic: Unicorn Rescue

A Java Swing-based RPG adventure game where you play as a healer mage on a quest to save a wounded unicorn by collecting magical ingredients and battling enemies across mystical locations.

## Game Overview

In **Mage Medic: Unicorn Rescue**, you embark on an epic journey through dangerous lands to gather three magical ingredients needed to heal a wounded unicorn. Along the way, you'll face progressively stronger enemies, collect mana, and ultimately confront powerful bosses including the fearsome Flame Guardian and the Evil Sorcerer.

## Features

### Core Gameplay
- **Character Creation**: Customize your character with name, pronouns, and age
- **Mana System**: Collect mana from defeated enemies (0-100 scale)
- **Mana Healing**: Convert stored mana to health points (1:1 ratio)
- **Progressive Difficulty**: Enemies become stronger with each battle won
- **Multiple Locations**: Explore Ancient Moongrove, Dwarven Mines, Volcanic Ruins, and Keebler Forest
- **Ingredient Collection**: Find Moonpetal, Dwarfstone, and Elvishberry

### Combat System
Four combat actions available during battles:
1. **Attack** - Standard damage dealing
2. **Defend & Heal** - Defensive stance with HP regeneration
3. **Special Attack** - High damage attack (2x attack power)
4. **Ultimate Attack** - Devastating move requiring 80 mana, deals 99% of enemy's current HP

### Boss Battles
- **Flame Guardian** (Mid-Boss): 100 HP, 22 ATK, 13 DEF
  - Guards the Volcanic Ruins
  - Rewards full mana restoration upon defeat
- **Evil Sorcerer** (Final Boss): 77 HP, 17 ATK, 9 DEF
  - Final challenge after healing the unicorn

### GUI Features
- Dark-themed Swing interface
- Real-time health and mana bars with color-coded indicators
- Character status tracking
- Inventory management system
- Location visit history

## How to Play

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Windows, macOS, or Linux operating system

### Running the Game

1. **Clone the repository**:
```bash
git clone https://github.com/MitchellBennett94/Mage-Medic-Unicorn-Rescue.git
cd Mage-Medic-Unicorn-Rescue
```

2. **Compile the game**:
```bash
javac *.java
```

3. **Launch the GUI version**:
```bash
java GameGUI
```

4. **Or play the console version**:
```bash
java Main
```

## Gameplay Tips

1. **Manage Your Mana**: Save mana for healing when health is low, or use the Ultimate Attack for tough enemies
2. **Progressive Strategy**: Early battles are easier - use them to build mana reserves
3. **Boss Preparation**: Face the Flame Guardian only when you have good HP and mana
4. **Special Rewards**: Defeating the Flame Guardian fully restores your mana
5. **Healing Spell**: Pay attention to the riddle - choose the spell related to "light and life"

## Game Statistics

- **Mana Rewards**: 10-25 per regular enemy, 100 for Flame Guardian
- **Enemy Scaling**: +3 HP, +1 ATK, +0.6 DEF per battle won
- **Ultimate Attack Cost**: 80 mana (80% of max mana)

## Game Progression

1. **Character Creation** → Customize your hero
2. **Prologue** → Learn about the wounded unicorn
3. **Exploration Phase** → Visit locations to collect ingredients
4. **Random Encounters** → 50% chance of battle at each location
5. **Boss Battle (Optional)** → Face the Flame Guardian at Volcanic Ruins
6. **Healing Ritual** → Use collected ingredients and cast the correct spell
7. **Final Battle** → Defeat the Evil Sorcerer
8. **Victory** → Save the unicorn and complete your quest!

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Architecture**: Object-oriented design with separate classes for game components
- **Combat**: Turn-based battle system with manual player control
- **Save System**: Auto-compiles to preserve game state
