# Crypto App Case Study

## Crypto Feed Feature Specs

## BDD Specs

### Story: User requests to see crypto feed toplist in 24 hours

### Narrative #1

```
As a online user
I want the app to automatically load the crypto feed
So I can see the newest crypto feed
```

#### Scenarios (Acceptance criteria)

```
Given the user has connectivity
When the user requests to see the crypto feed
Then the app should display the latest crypto feed from remote
```

## Use Cases

### Load Crypto Feed From Remote Use Case

#### Data:
- URL

#### Primary course (happy path):
1. Execute "Load Crypto Feed" command with above data.
2. System downloads data from the URL.
3. System validates downloaded data.
4. System creates crypto feed from valid data.
5. System delivers crypto feed.

#### Empty data course (sad path):
1. System delivers no crypto feed.

#### Invalid data – error course (sad path):
1. System delivers invalid data error.

#### No connectivity – error course (sad path):
1. System delivers connectivity error.

#### Unauthorized – error course (sad path):
1. System delivers unauthorized error.

#### Bad request – error course (sad path):
1. System delivers bad request error.

#### Server error – error course (sad path):
1. System delivers server error.

## Model Specs

### Crypto Feed

| Property       | Type     |
|----------------|----------|
| `CoinInfo`     | `Object` |
| `Raw`          | `Object` |

##### CoinInfo
| Property       | Type     |
|----------------|----------|
| `Id`           | `String` |
| `Name`         | `String` |
| `FullName`     | `String` |

##### Raw
| Property       | Type     |
|----------------|----------|
| `USD`          | `Object` |

##### USD
| Property       | Type     |
|----------------|----------|
| `PRICE`        | `Double` |
| `CHANGEPCTDAY` | `Long`   |

### Payload contract

```
GET /data/top/totaltoptiervolfull

200 RESPONSE

{
    "Message": "Success",
    "Data": [
        {
            "CoinInfo": {
                "Id": "7605",
                "Name": "ETH",
                "FullName": "Ethereum"
            },
            "RAW": {
                "USD": {
                    "PRICE": 2089.29,
                    "CHANGEPCTDAY": -0.14911035600096975
                }
            }
        },
        {
            "CoinInfo": {
                "Id": "1182",
                "Name": "BTC",
                "FullName": "Bitcoin"
            },
            "RAW": {
                "USD": {
                    "PRICE": 2089.29,
                    "CHANGEPCTDAY": -0.14911035600096975
                }
            }
        }
    ]
}
```