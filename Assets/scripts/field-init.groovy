List fields = [
	[id:1,//胶之林
		data:'assets/scripts/field/fore-3.json'
	],
	[id:2,//竹之林
		data:'assets/scripts/field/fore-2.json'
	],
	[id:3,//自由庭院
		data:'assets/scripts/field/fore-1.json'
	],
	[id:4,//里查登
		data:'assets/scripts/field/village-2.json'
	],
	[id:5,//废墟墓地
		data:'assets/scripts/field/ruin-4.json'
	],
	[id:6,//废墟
		data:'assets/scripts/field/ruin-3.json'
	],
	[id:7,//废墟村庄
		data:'assets/scripts/field/ruin-2.json'
	],
	[id:8,//遗忘之地
		data:'assets/scripts/field/ruin-1.json'
	],
	[id:9,//诅咒之地
		data:'assets/scripts/field/De-1.json'
	],
	[id:10,//内维斯克
		data:'assets/scripts/field/village-1.json'
	],
	[id:11,//绿洲
		data:'assets/scripts/field/De-2.json'
	],
	[id:12,//古代战场
		data:'assets/scripts/field/De-3.json'
	],
	[id:13,//封印之地
		data:'assets/scripts/field/De-4.json'
	],
	[id:14,//古代地牢1层
		data:'assets/scripts/field/Dun-1.json'
	],
	[id:15,//古代地牢2层
		data:'assets/scripts/field/Dun-2.json'
	],
	[id:16,//古代地牢3层
		data:'assets/scripts/field/Dun-3.json'
	],
	[id:17,//GM房间
		data:'assets/scripts/field/office.json'
	],
	[id:18,//心情树林
		data:'assets/scripts/field/forever-fall-04.json'
	],
	[id:19,//黄昏树林
		data:'assets/scripts/field/forever-fall-03.json'
	],
	[id:20,//秋之谷
		data:'assets/scripts/field/forever-fall-02.json'
	],
	[id:21,//风之路
		data:'assets/scripts/field/forever-fall-01.json'
	],
	[id:22,//菲尔拉
		data:'assets/scripts/field/pilai.json'
	],
	[id:23,//古代神殿1层
		data:'assets/scripts/field/dun-4.json'
	],
	[id:24,//古代神殿2层
		data:'assets/scripts/field/dun-5.json'
	],
	[id:25,//蘑菇洞穴
		data:'assets/scripts/field/Tcave.json'
	],
	[id:26,//蜂房洞穴
		data:'assets/scripts/field/Mcave.json'
	],
	[id:27,//古代圣殿
		data:'assets/scripts/field/Dcave.json'
	],
	[id:28,//没落都市
		data:'assets/scripts/field/iron-1.json'
	],
	[id:29,//普隆的心脏
		data:'assets/scripts/field/iron-2.json'
	],
	[id:30,//幽拉村庄
		data:'assets/scripts/field/ice_ura.json'
	],
	[id:31,//SOD
		data:'assets/scripts/field/sod-1.json'
	],
	[id:32,//凯拉笔山谷
		data:'assets/scripts/field/ice1.json'
	],
	[id:33,//四转任务
		data:'assets/scripts/field/quest_IV.json'
	],
	[id:34,//祝福城堡
		data:'assets/scripts/field/castle.json'
	],
	[id:35,//贪婪湖泊
		data:'assets/scripts/field/Greedy.json'
	],
	[id:36,//风之谷
		data:'assets/scripts/field/ice_2.json'
	],
	[id:37,//凯尔祖维的巢穴
		data:'assets/scripts/field/boss.json'
	],
	[id:38,//迷失之地
		data:'assets/scripts/field/lostisland.json'
	],
	[id:39,//迷失寺庙
		data:'assets/scripts/field/lost_temple.json'
	],
	[id:40,//场景图
		data:'assets/scripts/field/Fall_Game.json'
	],
	[id:41,//无尽之塔1层
		data:'assets/scripts/field/dun-7.json'
	],
	[id:42,//无尽之塔2层
		data:'assets/scripts/field/dun-8.json'
	],
	[id:43,//古代神殿3层
		data:'assets/scripts/field/Dun-6a.json'
	],
	[id:44,//无尽之塔3层
		data:'assets/scripts/field/dun-9.json'
	]
]

def slurper = new groovy.json.JsonSlurper()
List data = []
for (field in fields) {
	def text = new File(field.data).text
	def obj = slurper.parseText(text)
	data.add(obj)
}

output = data