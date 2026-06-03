# Honor of Kings IMS - Manual Test Cases

Test date: 2026-06-04  
Test environment: Windows PowerShell, JDK 21.0.10

## Test 01: Compile Project

Function tested: Java compilation

Input:

```powershell
javac -d out src\Main.java src\model\*.java src\service\*.java src\util\*.java
```

Expected output:

The project compiles successfully and class files are generated in the `out` folder.

Actual output:

The command completed successfully with no compiler errors.

Result:

Pass

Bug found:

None

## Test 02: Valid Admin Login

Function tested: Authentication and admin menu

Input:

```text
1
A001
admin123
0
```

Expected output:

The system logs in as Admin and displays admin-only menu options.

Actual output:

The system displayed `Login successful. Welcome, Admin.` and showed `8. Admin data management` and `9. Save data`.

Result:

Pass

Bug found:

None

## Test 03: Invalid Login

Function tested: Authentication error handling

Input:

```text
1
A001
wrong
0
```

Expected output:

The login is rejected and the program returns to the login menu.

Actual output:

The system displayed `Login failed. Check ID and password.` and returned to the login menu.

Result:

Pass

Bug found:

None

## Test 04: Player Lookup by ID

Function tested: Player lookup

Input:

```text
1
A001
admin123
1
P001
0
```

Expected output:

The system displays player ID, name, team, level, win rate, owned heroes, and equipment for each hero.

Actual output:

The system displayed `P001 | Chen Wei | team=Red Phoenix | level=38 | winRate=70.0%`. It also displayed owned heroes including Li Bai, Hou Yi, and Angela, with compatible equipment under each hero.

Result:

Pass

Bug found:

None

## Test 05: Team Overview by ID

Function tested: Team overview

Input:

```text
1
A001
admin123
2
T001
0
```

Expected output:

The system displays team name, members, average level, total matches, win rate, and top player.

Actual output:

The system displayed `T001 | Red Phoenix | members=5 | avgLevel=35.4 | matches=485 | winRate=64.7% | topPlayer=Zhou Yu`, followed by all five team members.

Result:

Pass

Bug found:

None

## Test 06: Hero Details by Name

Function tested: Hero details

Input:

```text
1
A001
admin123
3
Li Bai
0
```

Expected output:

The system displays hero name, type, base stats, compatible equipment, and players who own the hero.

Actual output:

The system displayed `H001 | Li Bai | ASSASSIN | atk=92 | def=48 | hp=3200`, compatible equipment, and owners including Chen Wei, Guo Chen, and Song Kai.

Result:

Pass

Bug found:

None

## Test 07: Equipment Statistics

Function tested: Equipment ranking

Input:

```text
1
A001
admin123
4
0
```

Expected output:

The system displays equipment ranked by the documented formula.

Actual output:

The system displayed `Equipment ranking formula: usageCount * 2 + rating + power / 100.` The first ranked item was `E012 | Dragon Spear | ATTACK | power=195 | rating=4.5 | usage=3 | score=12.45`.

Result:

Pass

Bug found:

None

## Test 08: Leaderboard by Win Rate

Function tested: Leaderboard sorting by win rate

Input:

```text
1
A001
admin123
6
3
1
0
```

Expected output:

The system displays the top 3 players by win rate and explains tie handling.

Actual output:

The system displayed top players:

```text
P003 | Zhou Yu | team=Red Phoenix | level=41 | winRate=73.6%
P014 | Qin Mo | team=Silver Moon | level=40 | winRate=72.2%
P006 | Xu Ran | team=Blue Dragon | level=39 | winRate=70.8%
```

Result:

Pass

Bug found:

None

## Test 09: Leaderboard by Level

Function tested: Leaderboard sorting by level

Input:

```text
1
A001
admin123
6
3
2
0
```

Expected output:

The system displays the top 3 players by level.

Actual output:

The system displayed the level-based leaderboard. The top players were Zhou Yu, Qin Mo, and Xu Ran.

Result:

Pass

Bug found:

None

## Test 10: Leaderboard by Number of Matches

Function tested: Leaderboard sorting by number of matches

Input:

```text
1
A001
admin123
6
3
3
0
```

Expected output:

The system displays the top 3 players by number of matches.

Actual output:

The system displayed the match-count leaderboard and applied the documented tie handling text.

Result:

Pass

Bug found:

None

## Test 11: Match History for Player

Function tested: Player match history, win/loss summary, and hero pick rate

Input:

```text
1
A001
admin123
5
P
3
P001
0
```

Expected output:

The system displays the last 3 matches for P001, including opponent, date, result, picked hero, win/loss summary, and hero pick rate.

Actual output:

The system displayed:

```text
Wins=2, Losses=2
M001 | Red Phoenix vs Opponent 1 | 2026-06-03 | WIN
  Picked hero: Li Bai
M004 | Red Phoenix vs Opponent 4 | 2026-05-31 | LOSS
  Picked hero: Li Bai
M007 | Red Phoenix vs Opponent 7 | 2026-05-28 | WIN
  Picked hero: Li Bai
Hero pick rates:
  Li Bai: 100.0%
```

Result:

Pass

Bug found:

None

## Test 12: Match History for Team

Function tested: Team match history and win/loss summary

Input:

```text
1
A001
admin123
5
T
5
T001
0
```

Expected output:

The system displays recent matches for team T001, with win/loss summary and hero picks.

Actual output:

The system displayed `Wins=2, Losses=1` after match M010 was deleted during the admin deletion test. It listed recent Red Phoenix matches and each player's picked hero.

Result:

Pass

Bug found:

None

## Test 13: Player Role Blocked from Admin Management

Function tested: Role-based access control

Input:

```text
1
P001
p001
8
0
```

Expected output:

The player user cannot use admin data-management functions.

Actual output:

The system displayed `Only admins can manage data.`

Result:

Pass

Bug found:

None

## Test 14: Player Edits Own Name

Function tested: Player self-service information editing

Input:

```text
1
P001
p001
7
Chen Test
0
```

Expected output:

The player can update their own name.

Actual output:

The system displayed the player's profile, accepted `Chen Test`, and displayed `Name updated.`

Result:

Pass

Bug found:

None

## Test 15: Admin Deletes Match Record

Function tested: Admin data management

Input:

```text
1
A001
admin123
8
5
M010
0
```

Expected output:

The admin can delete match record M010.

Actual output:

The system displayed `Deleted.` After deletion, team T001 match history no longer included M010.

Result:

Pass

Bug found:

None

## Test 16: Save Data Summary and CSV Files

Function tested: File I/O and persistence output

Input:

```text
1
A001
admin123
9
0
```

Expected output:

The system saves a readable data summary and structured CSV files.

Actual output:

The system displayed `Saved to docs/data-summary.txt and data/*.csv`.

Generated files:

```text
docs/data-summary.txt
data/users.csv
data/players.csv
data/heroes.csv
data/equipment.csv
data/teams.csv
data/team-members.csv
data/player-heroes.csv
data/hero-equipment.csv
data/match-records.csv
data/match-picks.csv
```

Result:

Pass

Bug found:

None

## Test 17: Initial Dataset Counts

Function tested: Initial dataset requirements

Input:

Program startup using `DataInitializer.createSampleData()`.

Expected output:

The project should contain at least 3 teams, 10 players, 15 heroes, 20 equipment items, and 10 match records.

Actual output:

The initialized dataset contains 3 teams, 15 players, 15 heroes, 20 equipment items, and 10 match records. Lookup and overview tests confirmed that teams have 5 members and players own multiple heroes.

Result:

Pass

Bug found:

None

## Test 18: Missing Player Search

Function tested: Missing record handling

Input:

```text
1
A001
admin123
1
UNKNOWN_PLAYER
0
```

Expected output:

The system shows a clear message instead of crashing.

Actual output:

The system displayed `Player not found.`

Result:

Pass

Bug found:

None

## Test Summary

Total test cases: 18

- Pass: 18
- Partial Pass: 0
- Fail: 0

Main notes:

- The previous match history issue has been fixed. The current output includes win/loss summary and hero pick rate for player match history.
- The persistence feature now saves both a readable summary and structured CSV files.
