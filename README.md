# JPsTale (Priston Tale in Java)
 Redevelop a 3D MMORPG "Priston Tale" in Java language, with free 3D game engine **jMonkeyEngine**

使用jMonkeyEngine引擎，对3DMMORPG游戏"Priston Tale"进行二次开发。目前此项目分成若干子项目，并行推进中。

## AssetLoader

这是用于解析精灵模型文件的jME3插件。

**AseLoader**

* 解析.ase模型记录的网格/骨骼/动画等数据。

**InxLoader**

* 解析.inx格式记录的动画索引。
* 解析.smd格式文件记录的3D网格数据。
* 解析.smb格式文件记录的骨骼动画数据。

## 资源解析

**Assets**

* 解析精灵的各种资源文件，诸如1. 地图/装备/怪物/NPC/贴图/音频/特效/天空

**DataEntity**

* 重新设计精灵的数据结构，并定义相对精简的数据实体。

## 功能性工具

**Aging**

* 锻造模拟器
* 用于调节服务器的锻造成功率
* 模拟锻造，用于评估在不同成功率下锻造所需金钱数量/道具数量的期望值。

**Craft**

* 合成模拟器
* 添加/查询/修改合成配方

**Monsters**

* 怪物编辑器
* 添加/查询/修改怪物属性
* 使用报表统计不同怪物的属性

**NPC**

* NPC管理器
 * 添加/编辑/移除NPC
 * 查询/编辑NPC的售卖物品列表

**Loots**

* 掉落管理
* 用于调整怪物的物品掉落表
* 设置掉率
* 查询怪物的掉落表
* 根据物品，查询哪些怪物可以掉落对应物品
* 统计工具：统计玩家打出某道具可能需要的时间和杀怪数量(期望值)。

**Field**

* 此项目用于视化编辑精灵地图数据。
* 可以添加/移除大地图。
* 可以添加/编辑/移除传送门。
* 可以编辑/预览服务器设置的地图刷怪点。
* 可以添加/移除地图中的NPC。
 
## GUI

**GUI**

* 使用Lemur+Groovy脚本，重新制作游戏的主界面。
