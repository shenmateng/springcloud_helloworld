package com.mt.service;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mt.database.raspconfig.Behavior;
import com.mt.database.raspconfig.RaspSwitchsStatus;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2024/9/12 11:10
 * @Version:
 * @Description:
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
public class test111 {

    public static void main(String[] args) {

        String oldRaspConfig = "{\n" +
                "\t\"_version\": \"20230627\",\n" +
                "\t\"rules\": {\n" +
                "\t\t\"ban\": {\n" +
                "\t\t\t\"file_write\": [\n" +
                "\n" +
                "\t\t\t],\n" +
                "\t\t\t\"wls_t3\": [\n" +
                "\n" +
                "\t\t\t],\n" +
                "\t\t\t\"wls_iiop\": [\n" +
                "\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t\"traffic\": {\n" +
                "\t\t\t\"method\": [\n" +
                "\t\t\t\t\"PUT\",\n" +
                "\t\t\t\t\"DELETE\",\n" +
                "\t\t\t\t\"COPY\",\n" +
                "\t\t\t\t\"MOVE\",\n" +
                "\t\t\t\t\"SEARCH\",\n" +
                "\t\t\t\t\"TRACE\",\n" +
                "\t\t\t\t\"PROPFIND\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"header\": {\n" +
                "\n" +
                "\t\t\t},\n" +
                "\t\t\t\"user\": [\n" +
                "\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t\"behavior\": {\n" +
                "\t\t\t\"connection\": [\n" +
                "\t\t\t\t\"4NzcpYLj6M+g3Mzep93a282nz93h55jT0+OPxq2vuOHK1ujEoI3c1Oqtzt7hxtXeyOTn\",\n" +
                "\t\t\t\t\"2tPU2pqT1tDfmNLq55fS6MOdjdrT1NqNs+LP4M/C6eLY1qjCpsTP49i9ztPexuY=\",\n" +
                "\t\t\t\t\"1uPn2tKrlqnm3s/ovNXR382ojbzU6g==\",\n" +
                "\t\t\t\t\"1uPn2pqTu9Xm2qLh4s7W7o2CxOU=\",\n" +
                "\t\t\t\t\"1d7j0sTiroTl382j587cqNar1pzf5dnT39bQ3pjG5OnRzeyNe87e19jctMK/pOHYzdrc3dHpzQ==\",\n" +
                "\t\t\t\t\"1OPjpYLj6M+g2MTpp+Df8Y2k0d3j4s3O3KHH5tqNu+3Zvcyrd87c3djN09niz6DNzuPnzsvu\",\n" +
                "\t\t\t\t\"5ufYpYLZ4NHemLfCxa7W7sio2LvQ4cvG1eWP5d7A5+2u1u7IqNg=\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"special\": [\n" +
                "\t\t\t\t\"4d/l083X0s7X3dLW4NLW4ZpXzuDWod3P4tzP2dDR1ubO3+nRn43b1ObdwNfcz9mY0t7m2ZbNyKHPu9Tm3cDX3M/ZvsTi6dXJ7sRiw93C2NjD\",\n" +
                "\t\t\t\t\"2Njly77d1MrepYLY6NaW29OgwOHi3MvNnt3K5MuN4trS1KjBqcja09jcjcLYz9bP0d7n0LXbyKCw49Toz6jk2M6g3cTj3Q==\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"illegal\": [\n" +
                "\t\t\t\t\"3eHamMDg1MTaz43f2tzY39Fi0ePd59PM1aGx09HEuOjX3N/XqKjb39+lgtPiz+bP1+m0jJg=\",\n" +
                "\t\t\t\t\"5dTV1s7X3MSg3cTn79XN7o2e0t6dw8vG1bbQ4N7E7e2y1erLb4LR3uHexOjnnJWa\",\n" +
                "\t\t\t\t\"0d7gmMjS4I/p3Y3s3svL6c2owNfd2NyN59jD09rPo9DOyrvPpJqRnZ22yOPnxuDP0ej10tbj053A2tjtz8Ps1tDg0MjctIyY\",\n" +
                "\t\t\t\t\"5dTV1s7X3MSg3cTn79XN7o2dzeLU5djA3KG019zV4d7dut/QqcTh47zXz9yuhNXZzene4dy1gmQ=\",\n" +
                "\t\t\t\t\"5dTV1s7X3MSg3cTn79XN7o2dzeLU5djA3KG418yg5em8zezVoMTisuLY09Xr1a2Nz93a3M21gmQ=\",\n" +
                "\t\t\t\t\"kd7l0bue1NHTzcfa1ZfL29OVy9fd1MaN0+LT18aNtunZ1OPCldPX3uGtzt7nxuqS0/Htr8ndwJjEl6qWzc7e58bq3pqYqQ==\",\n" +
                "\t\t\t\t\"3eHamMTT38ri3cSj487c7thi0tPh6dbE5KG019zV4d7dsNvNmMvT4a6NvtnhyubTwOHi483emleP\",\n" +
                "\t\t\t\t\"3eHamMTT38ri3cSj487c7thi1OLY35jC3+DR4djE4+2XqdzSqNHP0ue2yNbYpOvNy9q0jMft05XT06qWmg==\",\n" +
                "\t\t\t\t\"kdLi17ue1sLnzcfk1Zfb39GqxODLoeHE0tTR4saNndDOyrvPpNuv3+PWyNPU1dvZzZ60jMfgyKDT0+HAy83R2sbk5r7b4tXc39GBwN7f2Nzbz9/Q2dPNu+LV3N/RgcDe39jc28/qxtSrz+XF0tvuxKLE4OLvydLV5dfez9PC2tnY39Fvgp4=\",\n" +
                "\t\t\t\t\"196h383U2NPm2daj7M7a8MuZ05zS4tzEnsDC4MvG2t28zezVoMTi4q6NzNHhwtnPw8je297mxKisz9+ujY8=\",\n" +
                "\t\t\t\t\"196h383U2NPm2daj7M7a8MuZ05zS4tzEnsDC4MvG2t2v0ebTmdHhqpbXwN7UyNfOpd7l3c3srJXPqZKj\",\n" +
                "\t\t\t\t\"196h383U2NPm2daj7M7a8MuZ05zS4tzEnrTR4tbI2Nrd0enNgMjh49jYxOLmnJXLy+HF0tvuxKLE4OKujY8=\",\n" +
                "\t\t\t\t\"3eHamNLg5crg0cXn2tbN8c6mypzm2MyN49jT6NbE6afRyejDoMTgnbTM0uTlwtXep9bnzdTf0YHA3t/c2MarlsLWy8/p3s2x6NOZ0dHU497O4ubd29jT2uvMzerTo9Hhqpaa\",\n" +
                "\t\t\t\t\"kdLi17ue59Dg0dba28WWotaZwcqd1tnR1e/Y18y7o+3R1+zbl8Di0N/TzdGcvaDNzufexZa7z6TL19LU3sjf4aTh2NPa8ZHc9tN6wNHQ18+Iq5bE4djT2vHdo52P\",\n" +
                "\t\t\t\t\"2NDpy43c1M/ZmLPd687J3qamzuPfro3T2OXG087SsJyZ\",\n" +
                "\t\t\t\t\"4eThmMzZ5sSgv83o2s/NtYKox9PE4d3A1ticlZo=\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"dns\": [\n" +
                "\t\t\t\t\".ceye.io\",\n" +
                "\t\t\t\t\".dnslog.cn\",\n" +
                "\t\t\t\t\".admin.dnslog.link\",\n" +
                "\t\t\t\t\".dnslog.io\",\n" +
                "\t\t\t\t\".burpcollaborator.net\",\n" +
                "\t\t\t\t\".hyuga.co\",\n" +
                "\t\t\t\t\".tu4.org\",\n" +
                "\t\t\t\t\".h.i.ydscan.net\",\n" +
                "\t\t\t\t\".dns1.tk\",\n" +
                "\t\t\t\t\".dnsbin.zhack.ca\",\n" +
                "\t\t\t\t\".s0x.cn\",\n" +
                "\t\t\t\t\".t00ls.com\",\n" +
                "\t\t\t\t\".xip.io\",\n" +
                "\t\t\t\t\".xip.name\",\n" +
                "\t\t\t\t\".exeye.io\",\n" +
                "\t\t\t\t\".sslip.io\",\n" +
                "\t\t\t\t\".nip.io\",\n" +
                "\t\t\t\t\".bxss.me\",\n" +
                "\t\t\t\t\".godns.vip\",\n" +
                "\t\t\t\t\".ngrok.io\",\n" +
                "\t\t\t\t\".pipedream.net\",\n" +
                "\t\t\t\t\".hopto.org\",\n" +
                "\t\t\t\t\".myftp.org\",\n" +
                "\t\t\t\t\".servehttp.com\",\n" +
                "\t\t\t\t\".sytes.net\",\n" +
                "\t\t\t\t\".zapto.org\",\n" +
                "\t\t\t\t\".ddns.net\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"check_item\": [\n" +
                "\t\t\t\t\"4NTUzq7S3cbV3pqY48re242dzpy+1dTE0+eq4NrU6czd2t/AoY3g1NTOrtLdxtXempiqmpmrkGWakdPY3cTi3MLe09na\",\n" +
                "\t\t\t\t\"wtTg2svR58bls8zl5aSL8tKg09Gd59zA6KG119fP4drdze2ooc/aneHP1sTlwuDdxeTr1s3smleQn6Ckm5CrlsXX3cTn4srU49mZ\",\n" +
                "\t\t\t\t\"xbvGvsTd483T3sTowtbY5ppXwt3codnR0dbN15jW4eyX2+LAmMTSneLcxp7U0dPNx9qn4cnmwKKN5uLf3sKe59PT4o3J3tbY5sCoxOG44NrLnuXG086u1+POy+6aV5CfoKSbkKuWxdfdxOfiytTj2Zk=\",\n" +
                "\t\t\t\t\"0d7f1sTT58rh2NKwnNja4Y2Vz8/S28+N0+LO39nN6KfM1+bLmcLi2OLYh8zqjJuYxernzNzp0aeNlsvqlYjE5cLg3cXk69bN7I2o0c/d5tDO4uCclZuQpqqambWCmMTh1OXTwNzc29c=\",\n" +
                "\t\t\t\t\"4d/l083X2dPT18Ts6NvTtYKj0dWd5trR2eHI2NzA4t7g1+zKYsLd4diYstXlytPWyO/ay9TfwqS24NDj2sTil67X3sfk3bLW8M6fxNHfw9zO5tzF19yN597KzMnBnsTR466NkKGkkqObmpjdztvf0Z3A2tjtzw==\",\n" +
                "\t\t\t\t\"xsLn3MTR4JyVzc7ip93Q6dSbx+Lm4tzK46HZ5d7R2trWltKyqNHT0OCYxeLizsq3q7CcmpmrkGWQqZLXz9LV5crT1sjv3g==\",\n" +
                "\t\t\t\t\"59Dg1pqT4tPZmNjW5tWW7c2VytPo1NfLnszC39aN4ejKzLWCZZCfoKSbmpPXxuXP0d7a1dH0xA==\",\n" +
                "\t\t\t\t\"uMLCuJCrls/X3o3o35fS7c6ijbjCwriu0t3G1d6N2+vY1cnBnsTR466NkKGkkqObmpjdztvf0Z3A2tjtzw==\",\n" +
                "\t\t\t\t\"2NDW1dLf4ZyVzc7ip8/J7dOZ0ebc35jJ0dbM5dnNo93K3NvBnc3SncLMydXW1b/Lz+Xe25bsxJXDxNDf38SrlpKjm5CmqqSL3sSnxODY1NbI6tg=\",\n" +
                "\t\t\t\t\"1NDm3snj4s+tjcLk5pfJ5siWwNDQodDA4+fL5dnNo8O8t8iNpMDg4pvP29XCw9zPwun1zqns0ZXYl6qWm5ChpJKjpYLZ3tzN7MiVy9fp2A==\",\n" +
                "\t\t\t\t\"2N7Xzsnj4s+tjcnk3c2W5NKjzZy55tnNwNTT5c/Ro+nK2u3Eb4KfoKSbkKGuhNbP0trr0snmyK7E\",\n" +
                "\t\t\t\t\"1NvY4snj4s+tjcXh3uHS7c6ijbjCwrij1ebG5NPA4eLjzeyNmMTh1OXTwNzc29elgqaqmpmrkG+C0tTmz9HZ1M3b5MQ=\",\n" +
                "\t\t\t\t\"stjm1aXZ38a73sTitIzX7MZiwN7Q1tLEntbQ39fO4+yXzuPLmdTe2+LLw57XyuXVjbni3NPAyKDEt+PY143i2MLWucHf3szctYJkkJ6fo5qak9fG5c/R3trV0fTE\",\n" +
                "\t\t\t\t\"zbfU3cfC2MLWucHf3szctYKmxM/TwszJ1dbV0ZKNvdrc0O7AlsvTneXPwNS7wuXS09bb1c3ZiHONttDm0oe91NHuvcTpopfa38CYrtDZ2M3Tq5aSo5uQpqqki97Ep8Tg2NTWyOrY\",\n" +
                "\t\t\t\t\"zbfU3ce91NG6y9LdvNjM35BvgtbQ5tKi39fG0ZKNvdrc0MfApI3W0ObSvpmyj7rL0t3Gytioz6nTqZKkm5ChpJKtjcPa7M7a48CgyOjU\",\n" +
                "\t\t\t\t\"zbfU3ce91NG6y9LdvNjM35FvgtbQ5tKi39fG0ZKNvdrc0MfApI3W0ObSvpmyiaCywOjhtsnqjaTU4rXi3KLi2MLmz76euJew29KcrM/fodzE0dew1NTE2O2ki6uQZZCfoK6Nw9XmxuTTwOHi480=\",\n" +
                "\t\t\t\t\"zbfU3ce91NG329TW5dyZtYKZ0OPQ392+nrvC5dKs1umXkOrUqLXP2+/a1OS50OSt0dra3c2jvmKnz+Lbt8DgodPXy8PE29PN3dNvgp+gpJuQoa6E1s/S2uvSyebIrsQ=\",\n" +
                "\t\t\t\t\"zbfU3ce91NG329TW5dyatYKZ0OPQ392+mKGp093HwtrZlurUqLXP29KTnp67wuXSrNbpl9jv02+Cn6Ckm5ChroTWz9La69LJ5siuxA==\",\n" +
                "\t\t\t\t\"zbfU3cfE1MPez7Ha2s233MmZwuKqlpLE4ejC3t3b3drc0L3OmMSXzqGywOPb1dPMy9qn283dzqLS4tjn39PZ4s/C39PUoZew29Kc08/R38+N4tjC1rLA6OHdydzLmb6XrqGywOPb1dPMy9qn283bw4PB2NTW3pqTpJKjm5CmtIzM39KZ0dfQ39PZ1Q==\",\n" +
                "\t\t\t\t\"zbfU3cfE1MPez6/q7aSLosSl1M/b5ubH0ebJtdnD2qLIkKinldLW49TMy9WhydPdx9SiqJbCwKfH4tDV1sSe49bmpYKmqpqZq5BvgtLU5s/R2dTN2+TE\",\n" +
                "\t\t\t\t\"zbfU3cfE5cbXrc7i6cra35pXwt3c48vR1Zu14ea+o83bzd+slc+c0uLXz9HlxpvJjcnrzs3HwKSN3uTnpYKhpJKjm5CwnM3N7cSmyM/b3OTE\",\n" +
                "\t\t\t\t\"vuHc2dHZ59rD38Tq3qSL5MCqwJzk59PLnsPT29nR3u3iue/EqcSc4djLw7/Vy9fN07CcmpmrkGWQqZLXz9LV5crT1sjv3g==\",\n" +
                "\t\t\t\t\"sNDXq9Pk5crU39Paz8rU78R51960683E4OfK4diamOPK3tvXYszP3dTRxN3Yz+aYodbdqtzu0Z3B4+PYwMDc6Ma34s+68czN6tOdztyd5c/A1MLD3M/C6bSMmauQZZCfqpbOxOPY09vLy97zzg==\",\n" +
                "\t\t\t\t\"0aLjmpqT1tDfmMzY4crW4cRi1aCd4cvM2eHIoLzE297bzejCmcDQ29i/09nf1KDcxNve283owpmz3b7V1MTT55yVm5CmqpqZtYKYxOHU5dPA3Nzb1w==\",\n" +
                "\t\t\t\t\"uLHi3dK54dXX3MLa6d3X7NJlmpHY4d7E4tbG4t7O56fZ2unXrY233efP0dPY0ebZ0cLe3dDpw3zA3NPfz9Ge5cbTzq7X487L7ppXkJ+gpJuQq5bF193E5+LK1OPZmQ==\",\n" +
                "\t\t\t\t\"u97t08vc1LPa083kq6SL6dGbjdve7dPL3NSP3MvV1uzM2uPPqI280OfT1dW9wujLrtfjzsvujabEz9PCzMnV1tWtjZCmqpqZq5pXw9Pi2NzI0d/K7M8=\",\n" +
                "\t\t\t\t\"wuHYz6HR2pyV2dHcp8rY28KcxJzS4tfM3+HUoM3O4eXOy+7Io83ho6HMwNehteTPxLfa0JbsxJXDvdHdz8LkroSjm5CmqpqjncOZ0tPh3MvL2e3G\",\n" +
                "\t\t\t\t\"4tDh0c7j4s2tjcLk5pfc282bzuHe35jU5NzNoK/X6d7b1tvLndnP0d/Pp9Xf0dfcjefeyszJwZ7E0eOujZChpJKjm5qY3c7b39GdwNrY7c8=\",\n" +
                "\t\t\t\t\"teHi2dXproTh3Maj3NjM38eV1OGd2tzO3+naoNzU4+3S1d+NXKLd3enP0eTYxe63xOnh2MyjoqDO4eTlz5qTo5Gjmo+ltIzM39KZ0dfQ39PZ1Q==\",\n" +
                "\t\t\t\t\"r93h2dPR58rh2Kjj79jL29Odzty31NjD3NjTrY3S6ueX2t/FoMTR46HLzd7i1dPeyOTnl6nozaPTz+Pc2c254dfhzcDp4tjWwsCiw9rU5ZjR1dTFwczJ2tzdo52QZZCfoKSlgtTY1NfcyNbl0uLf\",\n" +
                "\t\t\t\t\"2N3X05qT3cLoy9ej58rV482bjbfd3N7I0d+k4djT2vHdlubOo8rj366NkKGkkaObmpjdztvf0Z3A2tjtzw==\",\n" +
                "\t\t\t\t\"xry/rsTT4sXX3JqY48re242WxM/d5pi3vb+l183O2d7bluzElcO90d3PwuSuhKObkKaqmqOdw5nS0+Hcy8vZ7cY=\",\n" +
                "\t\t\t\t\"xbvGs6i/w5yV4cTX5djP48JiyNfe45ioucKxu9jP6u283OzElcyc4djLw8/Uz+ulgqWqmpmrkG+C0tTmz9HZ1M3b5MQ=\",\n" +
                "\t\t\t\t\"xbDGs6i/w5yVzc7ip9LK542mzNed3NmNubywwrPN5e7du+7RmcDbnebTzODfxsTPwNnIy9LfwqiakaCkm5ChpJyVzsTo3tvR28ud2dM=\",\n" +
                "\t\t\t\t\"ttTm3cjR4arg2tTptIzL6cxiws/k1tLOntvG5d3I1ueX0emNXLvlmpyY0dXUxcHMydrc3aOdkGWQn6CkpYLU2NTX3MjW5dLi3w==\",\n" +
                "\t\t\t\t\"4N7gz5qT1tDfmNHk5s7c6c6g0pzh4tfEntnG186N3ubZ1Kizo7Li4dzYxrLYwuCY0+TM3drjzZuakaCkm5ChpJyVzsTo3tvR28ud2dM=\",\n" +
                "\t\t\t\t\"1NvY4pqT2c3X4o3i3tzb28adzdWd3NmN0eDHoJK77KSSluzElcO90d3PwuSuhKObkKaqmqOdw5nS0+Hcy8vZ7cY=\",\n" +
                "\t\t\t\t\"xtzfq8/g38rVy9Pe6Ner6c2oxObjro3S4OXK4NHF59rWzfHOpsqc0uLY09Xr1aDd1OXp2NrujVyl19vYvdjj58bf5qLh2tzbysCox5fH4Nag4OPN283A6eLY1r3OotPT5+eYm9nhyuaompiqmpmrkGWakdPY3cTi3MLe09na\",\n" +
                "\t\t\t\t\"vbW109mrltDk0Y3k38vR9I2WwOHUod/T2d+Px97I4cjL0t/CqI3V1Oe5wdrYxOav19je2dzjzqKakaCkm5ChpJyVzsTo3tvR28ud2dM=\",\n" +
                "\t\t\t\t\"4d/l083XuK2tjc7n4Jfb6tGdzdXV5cvM1erQ5NWN2vHZ2t/Sp8jd3aHdz9Xfj+Xfz+Xo29yosZnF2tTW3sjm2Im/z9Pd6M3kvc6i0uLh6M3T3+WKt+LE2O7d1+yNmdfT0ujexKuWkqObj6aqpIvf16TR0+Lm087e\",\n" +
                "\t\t\t\t\"u8W4tpqT4tPZmMzr3tWaqKyKpLqdm8/X1dbW5s+k7enbze3Snc7c69jgwNycnJWbkKapmpm1gpnX3uHY3dLZ4s8=\",\n" +
                "\t\t\t\t\"0d7bz9HV4cTXpYLY6NaW7sCixt3i4taN0+LJ19zE49zOlufVmcugncDApLyhidfixNju3c2/16TR0+Lm087e78boy8uetIyZq5BkkJ+qls/X4OXG5d3I5Oc=\",\n" +
                "\t\t\t\t\"2NTr1pKrltDk0Y3W6crL4sRiwt3c4NnN46HL1+LLqKfS1u7Eps3P26HTzeTl0OXaxNjt0tfojVys0+Pb2cO168bV39Pk6+Wr6c2n0+Dk1t7O4sDG5tLO2aKX0ejVo8rTqpabkKGjkqOlgtrx2drf0qfI3d0=\",\n" +
                "\t\t\t\t\"wdLl08/kuM/Z083atIzS3spizc/i29nR3qHC4tON6Nzb0erTnc3VncHL0tji0+C9wufi2dy/zZvI3NShz9XR36rf2suwnJqZq49lkKmS2OLP4tjU5dPO4w==\",\n" +
                "\t\t\t\t\"2NHi3dK1v5yV2dHcp9PK6dKnjdPbod/T2d+PxM/F4d7M3OPOorTi2N+YyN7p0N3PrNrt0dfemleQn6Cjm5Crlsbq2tHa7NzR6c0=\",\n" +
                "\t\t\t\t\"2NDpy9e1v5yV1MDr2uGW38tipLrE59PLntzP6NnK2qG2ze7Ho8PqsuLY0uTl1tXezueipIurkGWPn6CujcTo49PX3dLe6Nc=\",\n" +
                "\t\t\t\t\"3dbh1pqT4sjg1o3E4NfUzNSi09fc2JjC0d/NmqvP5evY2OzIldPTvNjex9/X3bXZzejt293d06PRl6qWm5Cho5KjpYLa8dna39KnyN3d\",\n" +
                "\t\t\t\t\"5NTf2cLZ59qtjc7n4JfJ6sCXx9Od6c/L39bK5uON5+7X3OPMmY3e0OXdxOKhz+HOxKO6vLzHxKjH3dOhz9fV1tbmz5qYqZmZqo9kmpHU69rR1ebU29nN\",\n" +
                "\t\t\t\t\"1d7X5Mjc38LR18Tp3tvY7MSoxOCqltrL5drK4Jis2u3O2urRmdPT4aHc1N6uhKKaj6apmaOd1pnB4dfY1ss=\",\n" +
                "\t\t\t\t\"1OHYz8zR5czX3JqY39vN38yV0dnU5ZjT1eDR3svT2qfe3OPLndPnnbjixNPo1delgqWpmpiqj2+C3ePbz9E=\",\n" +
                "\t\t\t\t\"z9Ln09XV4NLR2tTptIzX7MZiwN7Q1tLEntTE5tPV2ubalu/Tncucwdjd07bczebP0aPd2Ljv02+CnqCjmo+groTh3sfa6w==\",\n" +
                "\t\t\t\t\"u7vY3pqT3cLoy9ej5srW28aZzNPd55jL39TF29jGo8a1ze6Nm8TivLXPwN7mp+TZzMrLtaOdj2SPn5+jpYLf58nX3A==\",\n" +
                "\t\t\t\t\"2t7aq8LT2NTlpYLk69CW28+VwtbUoc3A5NTN29jAo9zY2t+Nd87c49TTzdXlo9PdxKPl2M+7wpfE4eKujY+ho5GimpqY6N3Q39FvgtHb1N3SnuDQ1t/L2qfM1NvSp6vd0NfP0Z7lxuXZ1OfcztuowqPN4tTr3o3g1NPX2NOj6dLY38udzdOd2dPR4+c=\",\n" +
                "\t\t\t\t\"0d7fzsXl5srh2L7h3crYtYKXztrT2d/S2eLPoN7A3N7h3KjNmdOcu9fLz8TUyKDOzsjtytrus5XGqZKjmpCgo5Gtjc7p4c7a\",\n" +
                "\t\t\t\t\"xbvGvKy5roTpz8Hh6NDR3Y2mzNed3NjT1eXP09aNt9rc0d2ymdHk1OW8xNahydPYw+Heu83r1JnS4qqWmo+ho5KipYLk7dHN7A==\",\n" +
                "\t\t\t\t\"4d7U2pqT1tDfmMjX5pff7Y2hwNzQ2s/M1eHVoM3O4+fOy+7Opo3h3tTajcPCosKtzuPnzsvuyKPNnNfU2MPc2LPX29Ta7N2jnY9kkJ6fo6WC3+fJ19w=\",\n" +
                "\t\t\t\t\"5ufYpYLZ4NHemLfCxa7W7sio2LvQ4cvG1eWP5d7A5+2u1u7IqNipkqSaj6Cjka2Nzunhzto=\",\n" +
                "\t\t\t\t\"uNDey9Hk1K7n1tPeycra7ppXzuDWocvP0dbJ15jS6eve3O2RYsPX4uPL09PbxuSYzOrl3dHqwKbTnLnU1cDi58K/38vp4rnJ7NOGxN/k2N3TntXW29bDuuvb1+ysmdLh0NrPmpOjkqOaj6W0jNfux5nR\",\n" +
                "\t\t\t\t\"29Tny9Lg39Db3pqY5s7c29Kky93Y55iv0ezN4cvDo+bK0eiaV4+eoKOaj6uW0ObSxOc=\",\n" +
                "\t\t\t\t\"teHi2dXpxsTk08/ptIzX7MZiydPd3tPN49bKoNrL6uDS1u2Np8Lg2OPe0tXW1uTT0+6n3Mnow5bO5p3a3M7f6dqgvcTY7tvNwdGjzuToxs3R2ePVlq7E6Nzb0erTo9G33OPWjdTipNrPwuDMzNrjz6iakZ+jm4+go5yV2dPd3tujnc6mxpzZ2NjK2eHU1dON5eXez+PNp43h0uXTz+TmxtXf0d7t4pbtwKLD0N7rmMbi4tDo443I3szd7MR70d3e6eOy0+XK4t6O2OHOy+Wyl9HX3+c=\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"sqli_item\": {\n" +
                "\t\t\t\t\"function\": \"dXBkYXRleG1sLGV4dHJhdnR2YWx1ZSxzbGVlcCxiZW5jaG1hcmsscGdfc2xlZXAsZ3JvdXBfY29uY2F0LGNvbmNhdF93cyxzdWJzdHIsc3Vic3RyaW5nLG1pZCxsZW5ndGgsb3JkLGFzY2lpLGhleCxiaW4scmFuZCxyaWdodCxsZWZ0LGNoYXIsY2hyLGNvbmNhdA==\",\n" +
                "\t\t\t\t\"keyword\": \"c2VsZWN0LWZyb20saW5zZXJ0LWludG8sdXBkYXRlLXNldCxkZWxldGUtZnJvbSxjcmVhdGUtdGFibGUsaGFuZGxlci1vcGVuLHJlbmFtZS10YWJsZSx1bmlvbi1zZWxlY3Qsc2VsZWN0LWludG8sYW5kLG9yLHhvcixub3Q=\",\n" +
                "\t\t\t\t\"symbol\": \"JiYsfHwsLS0sJyM=\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"sensitive\": {\n" +
                "\t\t\t\"type\": [\n" +
                "\t\t\t\t\"text/html\",\n" +
                "\t\t\t\t\"application/json\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"words\": [\n" +
                "\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"switchs\": {\n" +
                "\t\t\"global\": {\n" +
                "\t\t\t\"desc\": \"全局开关\",\n" +
                "\t\t\t\"switch\": 2\n" +
                "\t\t},\n" +
                "\t\t\"ban\": {\n" +
                "\t\t\t\"file_write\": {\n" +
                "\t\t\t\t\"switch\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wls_t3\": {\n" +
                "\t\t\t\t\"switch\": 2\n" +
                "\t\t\t},\n" +
                "\t\t\t\"wls_iiop\": {\n" +
                "\t\t\t\t\"switch\": 2\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"traffic\": {\n" +
                "\t\t\t\"switch\": 0,\n" +
                "\t\t\t\"method\": {\n" +
                "\t\t\t\t\"switch\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"header\": {\n" +
                "\t\t\t\t\"switch\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"user\": {\n" +
                "\t\t\t\t\"switch\": 0,\n" +
                "\t\t\t\t\"sub_switch\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"behavior\": {\n" +
                "\t\t\t\"command_exec\": {\n" +
                "\t\t\t\t\"desc\": \"命令执行\",\n" +
                "\t\t\t\t\"all\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止所有WEB请求执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"reflect\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过反射执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过反序列化执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过表达式执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"any\": {\n" +
                "\t\t\t\t\t\"desc\": \"5、禁止通过请求参数执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"6、禁止通过 Webshell 执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"other\": {\n" +
                "\t\t\t\t\t\"desc\": \"7、禁止通过特定规则执行命令\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"file_read\": {\n" +
                "\t\t\t\t\"desc\": \"任意文件读取\",\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反序列化读取文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过表达式读取文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"any\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过请求参数读取文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过 Webshell 读取文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"other\": {\n" +
                "\t\t\t\t\t\"desc\": \"5、禁止通过特定规则读取文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"file_write\": {\n" +
                "\t\t\t\t\"desc\": \"任意文件写入\",\n" +
                "\t\t\t\t\"reflect\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反射写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过反序列化写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过表达式写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"any\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过请求参数写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"5、禁止通过 Webshell 写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"other\": {\n" +
                "\t\t\t\t\t\"desc\": \"6、禁止通过特定规则写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"file_read_write\": {\n" +
                "\t\t\t\t\"desc\": \"任意文件读写\",\n" +
                "\t\t\t\t\"reflect\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反射写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过反序列化读写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过表达式读写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"any\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过请求参数读写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"5、禁止通过 Webshell 读写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"other\": {\n" +
                "\t\t\t\t\t\"desc\": \"6、禁止通过特定规则读写文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"file_upload\": {\n" +
                "\t\t\t\t\"desc\": \"任意文件上传\",\n" +
                "\t\t\t\t\"script\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过 multipart/form-data 方式上传脚本文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"jndi\": {\n" +
                "\t\t\t\t\"desc\": \"JNDI注入防护\",\n" +
                "\t\t\t\t\"rmi\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过JNDI发起 rmi 外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"ldap\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过JNDI发起 ldap 外连\",\n" +
                "\t\t\t\t\t\"switch\": 4\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"dns\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过JNDI发起 dns 查询\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"iiop\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过JNDI发起 iiop 外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"dns\": {\n" +
                "\t\t\t\t\"desc\": \"DNS 查询防护\",\n" +
                "\t\t\t\t\"all\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止黑名单内的域名查询(如 dnslog.cn、ceye.io 等)\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"xxe\": {\n" +
                "\t\t\t\t\"desc\": \"XXE外部实体加载\",\n" +
                "\t\t\t\t\"all\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止外部实体加载\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"protocol\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止 ftp:// 等异常协议加载外部实体\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过反序列化加载外部实体\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"connect\": {\n" +
                "\t\t\t\t\"desc\": \"非法外连防护\",\n" +
                "\t\t\t\t\"all\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止所有WEB请求发起外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过反序列化发起外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过表达式发起外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过 Webshell 发起外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"other\": {\n" +
                "\t\t\t\t\t\"desc\": \"5、禁止通过特定规则发起外连\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"ssrf\": {\n" +
                "\t\t\t\t\"desc\": \"SSRF 请求伪造防护\",\n" +
                "\t\t\t\t\"http\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止伪造 http 请求\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"gopher\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止伪造 gopher 请求\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"ognl\": {\n" +
                "\t\t\t\t\"desc\": \"ognl 表达式获取信息\",\n" +
                "\t\t\t\t\"all\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过 ognl 获取信息\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"memshell\": {\n" +
                "\t\t\t\t\"desc\": \"内存马注入防护\",\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反序列化注入内存马\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过表达式注入内存马\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过 Webshell 注入内存马\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"jni\": {\n" +
                "\t\t\t\t\"desc\": \"JNI 方式绕过 rasp\",\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过 Webshell 加载共享库\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"sqli\": {\n" +
                "\t\t\t\t\"desc\": \"SQL注入漏洞防护\",\n" +
                "\t\t\t\t\"all\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止 SQL 注入漏洞利用\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"jrmp\": {\n" +
                "\t\t\t\t\"desc\": \"JRMP 注入防护\",\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反序列化注入JRMP\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过表达式注入JRMP\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过 Webshell 注入JRMP\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"reflect\": {\n" +
                "\t\t\t\t\"desc\": \"非法反射防护\",\n" +
                "\t\t\t\t\"field\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反射访问指定的字段\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"list_file\": {\n" +
                "\t\t\t\t\"desc\": \"目录遍历防护\",\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反序列化遍历目录\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过表达式遍历目录\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过 Webshell 遍历目录\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"file_delete\": {\n" +
                "\t\t\t\t\"desc\": \"任意文件删除\",\n" +
                "\t\t\t\t\"deserialize\": {\n" +
                "\t\t\t\t\t\"desc\": \"1、禁止通过反序列化删除文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"expression\": {\n" +
                "\t\t\t\t\t\"desc\": \"2、禁止通过表达式删除文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"any\": {\n" +
                "\t\t\t\t\t\"desc\": \"3、禁止通过请求参数删除文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\t\"desc\": \"4、禁止通过 Webshell 删除文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"other\": {\n" +
                "\t\t\t\t\t\"desc\": \"5、禁止通过特定规则删除文件\",\n" +
                "\t\t\t\t\t\"switch\": 6\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t},\n" +
                "\t\t\"sensitive\": {\n" +
                "\t\t\t\"switch\": 0\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"list\": {\n" +
                "\t\t\"white\": {\n" +
                "\t\t\t\"domain\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"command\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"webshell\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"url\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"ip\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"memshell\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"path\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"reflect\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"list_file\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"sqli\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"black\": {\n" +
                "\t\t\t\"url\": {\n" +
                "\t\t\t\t\"rules\": [\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        String newRaspConfig = "{\n" +
                "    \"_version\":\"20240904\",\n" +
                "    \"rules\":{\n" +
                "        \"ban\":{\n" +
                "            \"file_write\":[\n" +
                "\n" +
                "            ],\n" +
                "            \"wls_t3\":[\n" +
                "\n" +
                "            ],\n" +
                "            \"wls_iiop\":[\n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "        \"behavior\":{\n" +
                "            \"connection\":[\n" +
                "                \"4NzcpYLj6M+g3Mzep93a282nz93h55jT0+OPxq2vuOHK1ujEoI3c1Oqtzt7hxtXeyOTn\",\n" +
                "                \"2tPU2pqT1tDfmNLq55fS6MOdjdrT1NqNs+LP4M/C6eLY1qjCpsTP49i9ztPexuY=\",\n" +
                "                \"1uPn2tKrlqnm3s/ovNXR382ojbzU6g==\",\n" +
                "                \"1uPn2pqTu9Xm2qLh4s7W7o2CxOU=\",\n" +
                "                \"1d7j0sTiroTl382j587cqNar1pzf5dnT39bQ3pjG5OnRzeyNe87e19jctMK/pOHYzdrc3dHpzQ==\",\n" +
                "                \"1OPjpYLj6M+g2MTpp+Df8Y2k0d3j4s3O3KHH5tqNu+3Zvcyrd87c3djN09niz6DNzuPnzsvu\",\n" +
                "                \"5ufYpYLZ4NHemLfCxa7W7sio2LvQ4cvG1eWP5d7A5+2u1u7IqNg=\"\n" +
                "            ],\n" +
                "            \"special\":[\n" +
                "                \"4d/l083X0s7X3dLW4NLW4ZpXzuDWod3P4tzP2dDR1ubO3+nRn43b1ObdwNfcz9mY0t7m2ZbNyKHPu9Tm3cDX3M/ZvsTi6dXJ7sRiw93C2NjD\",\n" +
                "                \"2Njly77d1MrepYLY6NaW29OgwOHi3MvNnt3K5MuN4trS1KjBqcja09jcjcLYz9bP0d7n0LXbyKCw49Toz6jk2M6g3cTj3Q==\",\n" +
                "                \"29Tny9Lg39Db3pqY5s7c29Kky93Y55iv0ezN4cvD\",\n" +
                "                \"29Tny8HR5satjc7n4JfQrI140dfl2NyN0+LP4M/C6Q==\",\n" +
                "\t\t\t\t\"xtzfq8/g38rVy9Pe6Ner6c2oxObjro3S4OXK4NHF59rWzfHOpsqc0uLY09Xr1aDd1OXp2NrujVyl19vYvdjj58bf5qLh2tzbysCox5fH4Nag4OPN283A6eLY1r3OotPT5+eYm9nhyuao\",\n" +
                "\t\t\t\t\"2NTh1cje5sDV1siwnNHd3tKjzZzh2NfO5NzP2Zii4drc2+PCd87b3NTYw8TlwuDdz+Tr3ZbsxJXD\",\n" +
                "\t\t\t\t\"4d7f3JqT4tPZmMDl2szQ342nztrhod/P1NTV15ii5ObW0e6zpsDR2tjcjeLozw==\",\n" +
                "                \"2NTh1cje5sDi1tTc4tfbtYKj0dWd3c/N29zP5c3Io+nV3eHIotKc4tbcyODn1NfN1Ofi3eGo0pXN0tHi4o3X5dDh4NijzMrW3sGj17fd58/R09jR5tnRo+jXtd/TnM7SstTWyw==\"\n" +
                "            ],\n" +
                "            \"illegal\":[\n" +
                "                \"3eHamMDg1MTaz43f2tzY39Fi0ePd59PM1aGx09HEuOjX3N/XqKjb39+lgtPiz+bP1+m0jJg=\",\n" +
                "                \"5dTV1s7X3MSg3cTn79XN7o2e0t6dw8vG1bbQ4N7E7e2y1erLb4LR3uHexOjnnJWa\",\n" +
                "                \"0d7gmMjS4I/p3Y3s3svL6c2owNfd2NyN59jD09rPo9DOyrvPpJqRnZ22yOPnxuDP0ej10tbj053A2tjtz8Ps1tDg0MjctIyY\",\n" +
                "                \"5dTV1s7X3MSg3cTn79XN7o2dzeLU5djA3KG019zV4d7dut/QqcTh47zXz9yuhNXZzene4dy1gmQ=\",\n" +
                "                \"5dTV1s7X3MSg3cTn79XN7o2dzeLU5djA3KG418yg5em8zezVoMTisuLY09Xr1a2Nz93a3M21gmQ=\",\n" +
                "                \"kd7l0bue1NHTzcfa1ZfL29OVy9fd1MaN0+LT18aNtunZ1OPCldPX3uGtzt7nxuqS0/Htr8ndwJjEl6qWzc7e58bq3pqYqQ==\",\n" +
                "                \"3eHamMTT38ri3cSj487c7thi0tPh6dbE5KG019zV4d7dsNvNmMvT4a6NvtnhyubTwOHi483emleP\",\n" +
                "                \"3eHamMTT38ri3cSj487c7thi1OLY35jC3+DR4djE4+2XqdzSqNHP0ue2yNbYpOvNy9q0jMft05XT06qWmg==\",\n" +
                "                \"kdLi17ue1sLnzcfk1Zfb39GqxODLoeHE0tTR4saNndDOyrvPpNuv3+PWyNPU1dvZzZ60jMfgyKDT0+HAy83R2sbk5r7b4tXc39GBwN7f2Nzbz9/Q2dPNu+LV3N/RgcDe39jc28/qxtSrz+XF0tvuxKLE4OLvydLV5dfez9PC2tnY39Fvgp4=\",\n" +
                "                \"196h383U2NPm2daj7M7a8MuZ05zS4tzEnsDC4MvG2t28zezVoMTi4q6NzNHhwtnPw8je297mxKisz9+ujY8=\",\n" +
                "                \"196h383U2NPm2daj7M7a8MuZ05zS4tzEnsDC4MvG2t2v0ebTmdHhqpbXwN7UyNfOpd7l3c3srJXPqZKj\",\n" +
                "                \"196h383U2NPm2daj7M7a8MuZ05zS4tzEnrTR4tbI2Nrd0enNgMjh49jYxOLmnJXLy+HF0tvuxKLE4OKujY8=\",\n" +
                "                \"3eHamNLg5crg0cXn2tbN8c6mypzm2MyN49jT6NbE6afRyejDoMTgnbTM0uTlwtXep9bnzdTf0YHA3t/c2MarlsLWy8/p3s2x6NOZ0dHU497O4ubd29jT2uvMzerTo9Hhqpaa\",\n" +
                "                \"kdLi17ue59Dg0dba28WWotaZwcqd1tnR1e/Y18y7o+3R1+zbl8Di0N/TzdGcvaDNzufexZa7z6TL19LU3sjf4aTh2NPa8ZHc9tN6wNHQ18+Iq5bE4djT2vHdo52P\",\n" +
                "\t\t\t\t\"2NDpy43c1M/ZmLTDwsG47M6XxOHiro3F3+XMs9jDuvHOy7WCZQ==\",\n" +
                "\t\t\t\t\"2NDpy43c1M/ZmK/n6MzN7dJ9zN7bro3C4tjC5s+amKo=\",\n" +
                "                \"4eThmMzZ5sSgv83o2s/NtYJvguLX2L/N49TH16WCpQ==\",\n" +
                "                \"3eHamMTT38ri3cSj487c7thi0tPh6dbE5KG019zV4d7dq+nNqMTm47vLzdTfxuSlgtTsztrwy5nTttDhzsvV5ZyVmg==\"\n" +
                "            ],\n" +
                "            \"dns\":[\n" +
                "                \".ceye.io\",\n" +
                "                \".dnslog.cn\",\n" +
                "                \".admin.dnslog.link\",\n" +
                "                \".dnslog.io\",\n" +
                "                \".burpcollaborator.net\",\n" +
                "                \".hyuga.co\",\n" +
                "                \".tu4.org\",\n" +
                "                \".h.i.ydscan.net\",\n" +
                "                \".dns1.tk\",\n" +
                "                \".dnsbin.zhack.ca\",\n" +
                "                \".s0x.cn\",\n" +
                "                \".t00ls.com\",\n" +
                "                \".xip.io\",\n" +
                "                \".xip.name\",\n" +
                "                \".exeye.io\",\n" +
                "                \".sslip.io\",\n" +
                "                \".nip.io\",\n" +
                "                \".bxss.me\",\n" +
                "                \".godns.vip\",\n" +
                "                \".ngrok.io\",\n" +
                "                \".pipedream.net\",\n" +
                "                \".hopto.org\",\n" +
                "                \".myftp.org\",\n" +
                "                \".servehttp.com\",\n" +
                "                \".sytes.net\",\n" +
                "                \".zapto.org\",\n" +
                "                \".ddns.net\",\n" +
                "\t\t\t\t\".oastify.com\"\n" +
                "            ],\n" +
                "            \"check_item\":[\n" +
                "                \"4NTUzq7S3cbV3pqY48re242dzpy+1dTE0+eq4NrU6czd2t/AoY3g1NTOrtLdxtXempipmpmrkGWakdPY3cTi3MLe09na\",\n" +
                "                \"wtTg2svR58bls8zl5aSL8tKg09Gd59zA6KG119fP4drdze2ooc/aneHP1sTlwuDdxeTr1s3smlePn6Ckm5CrlsXX3cTn4srU49mZ\",\n" +
                "                \"xbvGvsTd483T3sTowtbY5ppXwt3codnR0dbN15jW4eyX2+LAmMTSneLcxp7U0dPNx9qn4cnmwKKN5uLf3sKe59PT4o3J3tbY5sCoxOG44NrLnuXG086u1+POy+6aV4+foKSbkKuWxdfdxOfiytTj2Zk=\",\n" +
                "                \"0d7f1sTT58rh2NKwnNja4Y2Vz8/S28+N0+LO39nN6KfM1+bLmcLi2OLYh8zqjJuYxernzNzp0aeNlsvqlYjE5cLg3cXk69bN7I2o0c/d5tDO4uCclZuQpqqambWCmMTh1OXTwNzc29c=\",\n" +
                "                \"4d/l083X2dPT18Ts6NvTtYKj0dWd5trR2eHI2NzA4t7g1+zKYsLd4diYstXlytPWyO/ay9TfwqS24NDj2sTil67X3sfk3bLW8M6fxNHfw9zO5tzF19yN597KzMnBnsTR466Nj6GkkqObmpjdztvf0Z3A2tjtzw==\",\n" +
                "                \"xsLn3MTR4JyVzc7ip93Q6dSbx+Lm4tzK46HZ5d7R2trWltKyqNHT0OCYxeLizsq3q7CcmZmrkGWQqZLXz9LV5crT1sjv3g==\",\n" +
                "                \"59Dg1pqT4tPZmNjW5tWW7c2VytPo1NfLnszC39aN4ejKzLWCZJCfoKSbmpPXxuXP0d7a1dH0xA==\",\n" +
                "                \"uMLCuJCrls/X3o3o35fS7c6ijbjCwriu0t3G1d6N2+vY1cnBnsTR466Nj6GkkqObmpjdztvf0Z3A2tjtzw==\",\n" +
                "                \"2NDW1dLf4ZyVzc7ip8/J7dOZ0ebc35jJ0dbM5dnNo93K3NvBnc3SncLMydXW1b/Lz+Xe25bsxJXDxNDf38SrlpGjm5CmqqSL3sSnxODY1NbI6tg=\",\n" +
                "                \"1NDm3snj4s+tjcLk5pfJ5siWwNDQodDA4+fL5dnNo8O8t8iNpMDg4pvP29XCw9zPwun1zqns0ZXYl6qWmpChpJKjpYLZ3tzN7MiVy9fp2A==\",\n" +
                "                \"2N7Xzsnj4s+tjcnk3c2W5NKjzZy55tnNwNTT5c/Ro+nK2u3Eb4KeoKSbkKGuhNbP0trr0snmyK7E\",\n" +
                "                \"1NvY4snj4s+tjcXh3uHS7c6ijbjCwrij1ebG5NPA4eLjzeyNmMTh1OXTwNzc29elgqWqmpmrkG+C0tTmz9HZ1M3b5MQ=\",\n" +
                "                \"stjm1aXZ38a73sTitIzX7MZiwN7Q1tLEntbQ39fO4+yXzuPLmdTe2+LLw57XyuXVjbni3NPAyKDEt+PY143i2MLWucHf3szctYJkkJ6fo5qak9fG5c/R3trV0fTE\",\n" +
                "                \"zbfU3cfC2MLWucHf3szctYKmxM/TwszJ1dbV0ZKNvdrc0O7AlsvTneXPwNS7wuXS09bb1c3ZiHONttDm0oe91NHuvcTpopfa38CYrtDZ2M3Tq5aSo5uQpqqki97Ep8Tg2NTWyOrY\",\n" +
                "                \"zbfU3ce91NG6y9LdvNjM35BvgtbQ5tKi39fG0ZKNvdrc0MfApI3W0ObSvpmyj7rL0t3Gytioz6nTqZKkm5ChpJKtjcPa7M7a48CgyOjU\",\n" +
                "                \"zbfU3ce91NG6y9LdvNjM35FvgtbQ5tKi39fG0ZKNvdrc0MfApI3W0ObSvpmyiaCywOjhtsnqjaTU4rXi3KLi2MLmz76euJew29KcrM/fodzE0dew1NTE2O2ki6uQZZCfoK6Nw9XmxuTTwOHi480=\",\n" +
                "                \"zbfU3ce91NG329TW5dyZtYKZ0OPQ392+nrvC5dKs1umXkOrUqLXP2+/a1OS50OSt0dra3c2jvmKnz+Lbt8DgodPXy8PE29PN3dNvgp+gpJuQoa6E1s/S2uvSyebIrsQ=\",\n" +
                "                \"zbfU3ce91NG329TW5dyatYKZ0OPQ392+mKGp093HwtrZlurUqLXP29KTnp67wuXSrNbpl9jv02+Cn6Ckm5ChroTWz9La69LJ5siuxA==\",\n" +
                "                \"zbfU3cfE1MPez7Ha2s233MmZwuKqlpLE4ejC3t3b3drc0L3OmMSXzqGywOPb1dPMy9qn283dzqLS4tjn39PZ4s/C39PUoZew29Kc08/R38+N4tjC1rLA6OHdydzLmb6XrqGywOPb1dPMy9qn283bw4PB2NTW3pqTpJKjm5CmtIzM39KZ0dfQ39PZ1Q==\",\n" +
                "                \"zbfU3cfE1MPez6/q7aSLosSl1M/b5ubH0ebJtdnD2qLIkKinldLW49TMy9WhydPdx9SiqJbCwKfH4tDV1sSe49bmpYKmqpqZq5BvgtLU5s/R2dTN2+TE\",\n" +
                "                \"zbfU3cfE5cbXrc7i6cra35pXwt3c48vR1Zu14ea+o83bzd+slc+c0uLXz9HlxpvJjcnrzs3HwKSN3uTnpYKhpJKjm5CwnM3N7cSmyM/b3OTE\",\n" +
                "                \"vuHc2dHZ59rD38Tq3qSL5MCqwJzk59PLnsPT29nR3u3iue/EqcSc4djLw7/Vy9fN07CcmZmrkGWQqZLXz9LV5crT1sjv3g==\",\n" +
                "                \"sNDXq9Pk5crU39Paz8rU78R51960683E4OfK4diamOPK3tvXYszP3dTRxN3Yz+aYodbdqtzu0Z3B4+PYwMDc6Ma34s+68czN6tOdztyd5c/A1MLD3M/C6bSMmKuQZZCfqpbOxOPY09vLy97zzg==\",\n" +
                "                \"0aLjmpqT1tDfmMzY4crW4cRi1aCd4cvM2eHIoLzE297bzejCmcDQ29i/09nf1KDcxNve283owpmz3b7V1MTT55yVmpCmqpqZtYKYxOHU5dPA3Nzb1w==\",\n" +
                "                \"uLHi3dK54dXX3MLa6d3X7NJlmpHY4d7E4tbG4t7O56fZ2unXrY233efP0dPY0ebZ0cLe3dDpw3zA3NPfz9Ge5cbTzq7X487L7ppXj5+gpJuQq5bF193E5+LK1OPZmQ==\",\n" +
                "                \"u97t08vc1LPa083kq6SL6dGbjdve7dPL3NSP3MvV1uzM2uPPqI280OfT1dW9wujLrtfjzsvujabEz9PCzMnV1tWtjY+mqpqZq5pXw9Pi2NzI0d/K7M8=\",\n" +
                "                \"wuHYz6HR2pyV2dHcp8rY28KcxJzS4tfM3+HUoM3O4eXOy+7Io83ho6HMwNehteTPxLfa0JbsxJXDvdHdz8LkroSim5CmqpqjncOZ0tPh3MvL2e3G\",\n" +
                "                \"4tDh0c7j4s2tjcLk5pfc282bzuHe35jU5NzNoK/X6d7b1tvLndnP0d/Pp9Xf0dfcjefeyszJwZ7E0eOujY+hpJKjm5qY3c7b39GdwNrY7c8=\",\n" +
                "                \"teHi2dXproTh3Maj3NjM38eV1OGd2tzO3+naoNzU4+3S1d+NXKLd3enP0eTYxe63xOnh2MyjoqDO4eTlz5qTo5Gjmo+ltIzM39KZ0dfQ39PZ1Q==\",\n" +
                "                \"r93h2dPR58rh2Kjj79jL29Odzty31NjD3NjTrY3S6ueX2t/FoMTR46HLzd7i1dPeyOTnl6nozaPTz+Pc2c254dfhzcDp4tjWwsCiw9rU5ZjR1dTFwczJ2tzdo52PZZCfoKSlgtTY1NfcyNbl0uLf\",\n" +
                "                \"2N3X05qT3cLoy9ej58rV482bjbfd3N7I0d+k4djT2vHdlubOo8rj366Nj6GkkaObmpjdztvf0Z3A2tjtzw==\",\n" +
                "                \"xry/rsTT4sXX3JqY48re242WxM/d5pi3vb+l183O2d7bluzElcO90d3PwuSuhKKbkKaqmqOdw5nS0+Hcy8vZ7cY=\",\n" +
                "                \"xbvGs6i/w5yV4cTX5djP48JiyNfe45ioucKxu9jP6u283OzElcyc4djLw8/Uz+ulgqWqmpmrkG+C0tTmz9HZ1M3b5MQ=\",\n" +
                "                \"xbDGs6i/w5yVzc7ip9LK542mzNed3NmNubywwrPN5e7du+7RmcDbnebTzODfxsTPwNnIy9LfwqiakZ+km5ChpJyVzsTo3tvR28ud2dM=\",\n" +
                "                \"ttTm3cjR4arg2tTptIzL6cxiws/k1tLOntvG5d3I1ueX0emNXLvlmpyY0dXUxcHMydrc3aOdj2WQn6CkpYLU2NTX3MjW5dLi3w==\",\n" +
                "                \"4N7gz5qT1tDfmIfn6NbN7s6jy+Gd5dnM1e/U59iN6PLXzOPCldPX3uGTjdbYxtaYyOLp1ZbOzofT4Njh0aHV1M+g3s7I7dvR6MZvgp6gpJuQoa6E1s/S2uvSyebIrsQ=\",\n" +
                "                \"1NvY4pqT2c3X4o3i3tzb28adzdWd3NmN0eDHoJK77KSSluzElcO90d3PwuSuhKKbkKaqmqOdw5nS0+Hcy8vZ7cY=\",\n" +
                "                \"xtzfq8/g38rVy9Pe6Ner6c2oxObjro3S4OXK4NHF59rWzfHOpsqc0uLY09Xr1aDd1OXp2NrujVyl19vYvdjj58bf5qLh2tzbysCox5fH4Nag4OPN283A6eLY1r3OotPT5+eYm9nhyuaompipmpmrkGWakdPY3cTi3MLe09na\",\n" +
                "                \"vbW109mrltDk0Y3k38vR9I2WwOHUod/T2d+Px97I4cjL0t/CqI3V1Oe5wdrYxOav19je2dzjzqKakZ+km5ChpJyVzsTo3tvR28ud2dM=\",\n" +
                "                \"4d/l083XuK2tjc7n4Jfb6tGdzdXV5cvM1erQ5NWN2vHZ2t/Sp8jd3aHdz9Xfj+Xfz+Xo29yosZnF2tTW3sjm2Im/z9Pd6M3kvc6i0uLh6M3T3+WKt+LE2O7d1+yNmdfT0ujexKuWkqObkKaqpIvf16TR0+Lm087e\",\n" +
                "                \"u8W4tpqT4tPZmMzr3tWaqKyKpLqdm8/X1dbW5s+k7enbze3Snc7c69jgwNycnJWbkKaqmZm1gpnX3uHY3dLZ4s8=\",\n" +
                "                \"0d7bz9HV4cTXpYLY6NaW7sCixt3i4taN0+LJ19zE49zOlufVmcugncDApLyhidfixNju3c2/16TR0+Lm087e78boy8uetIyZq5Blj5+qls/X4OXG5d3I5Oc=\",\n" +
                "                \"2NTr1pqT4tPZmMDl2szQ342Xztvc4tjSnt3G6taHqPWbkbmNnc3i1OXYwNyhyuDe0eTs2c3d053O3J2bt8Tk29DWr9fa3N7c6dGwot3d5t7R5dbV4dys2u3R196IYsjc5eLVxKuWkqObkKWqpIvf16TR0+Lm087e\",\n" +
                "                \"wdLl08/kuM/Z083atIzS3spizc/i29nR3qHC4tON6Nzb0erTnc3VncHL0tji0+C9wufi2dy/zZvI3NShz9XR36rf2suwnJqZq5BkkKmS2OLP4tjU5dPO4w==\",\n" +
                "                \"2NHi3dK1v5yV2dHcp9PK6dKnjdPbod/T2d+PxM/F4d7M3OPOorTi2N+YyN7p0N3PrNrt0dfemleQn6CkmpCrlsbq2tHa7NzR6c0=\",\n" +
                "                \"2NDpy9e1v5yV1MDr2uGW38tipLrE59PLntzP6NnK2qG2ze7Ho8PqsuLY0uTl1tXezueipIurkGWQnqCujcTo49PX3dLe6Nc=\",\n" +
                "                \"3dbh1pqT4sjg1o3E4NfUzNSi09fc2JjC0d/NmqvP5evY2OzIldPTvNjex9/X3bXZzejt293d06PRl6qWm5ChpJGjpYLa8dna39KnyN3d\",\n" +
                "                \"5NTf2cLZ59qtjc7n4JfJ6sCXx9Od6c/L39bK5uON5+7X3OPMmY3e0OXdxOKhz+HOxKO6vLzHxKjH3dOhz9fV1tbmz5qYqZqZq49lmpHU69rR1ebU29nN\",\n" +
                "                \"teHi2dXpxsTk08/ptIzX7MZiwt3T2NLA5eaP2dzO5O/iluzUotPX3NiYwtHfzeXT09qnqsrt06bA0eO2y8vcxsrmz43Y2tXUtYJlkJ+go5uak9jZ4tzE6OzS1+g=\",\n" +
                "                \"wNfc2M7D1tPb2tO659DR6MRvgtHe4JjS5eGP5c3R3undluTAqsDh0uXTz+Shs9rTzeTMzNrjz6ik3Nbc2MSe2NfT1pqYqpqZq49lmpHU69rR1ebU29nN\",\n" +
                "                \"tOHYz8zR5czX3JqY39vN38yV0dnU5ZjC3+XGoK/X5evO2+3Io82c1OnLy6uWkqObkKWqpIvf16TR0+Lm087e\",\n" +
                "                \"1d7X5Mjc38LR18Tp3tvY7MSoxOCqltrL5drK4Jis2u3O2urRmdPT4aHc1N6uhKKaj6apmaOd1pnB4dfY1ss=\",\n" +
                "                \"1OHYz8zR5czX3JqY39vN38yV0dnU5ZjT1eDR3svT2qfe3OPLndPnnbjixNPo1delgqWpmpiqj2+C3ePbz9E=\",\n" +
                "                \"z9Ln09XV4NLR2tTptIzX7MZiwN7Q1tLEntTE5tPV2ubalu/Tncucwdjd07bczebP0aPd2Ljv02+CnqCjmo+groTh3sfa6w==\",\n" +
                "                \"u7vY3pqT3cLoy9ej5srW28aZzNPd55jL39TF29jGo8a1ze6Nm8TivLXPwN7mp+TZzMrLtaOdj2SPn5+jpYLf58nX3A==\",\n" +
                "                \"2t7aq8LT2NTlpYLk69CW28+VwtbUoc3A5NTN29jAo9zY2t+Nd87c49TTzdXlo9PdxKPl2M+7wpfE4eKujY+ho5GimpqY6N3Q39FvgtHb1N3SnuDQ1t/L2qfM1NvSp6vd0NfP0Z7lxuXZ1OfcztuowqPN4tTr3o3g1NPX2NOj6dLY38udzdOd2dPR4+c=\",\n" +
                "                \"0d7fzsXl5srh2L7h3crYtYKXztrT2d/S2eLPoN7A3N7h3KjNmdOcu9fLz8TUyKDOzsjtytrus5XGqZKjmpCgo5Gtjc7p4c7a\",\n" +
                "                \"xbvGvKy5roTpz8Hh6NDR3Y2mzNed3NjT1eXP09aNt9rc0d2ymdHk1OW8xNahydPYw+Heu83r1JnS4qqWmo+ho5GipYLk7dHN7A==\",\n" +
                "                \"4d7U2pqT1tDfmMjX5pff7Y2hwNzQ2s/M1eHVoM3O4+fOy+7Opo3h3tTajcPCosKtzuPnzsvuyKPNnNfU2MPc2LPX29Ta7N2jnY9kkJ6fo6WC3+fJ19w=\",\n" +
                "                \"5ufYpYLZ4NHemLfCxa7W7sio2LvQ4cvG1eWP5d7A5+2u1u7IqNipkqSaj6Cjka2Nzunhzto=\",\n" +
                "                \"uNDey9Hk1K7n1tPeycra7ppXzuDWocvP0dbJ15jS6eve3O2RYsPX4uPL09PbxuSYzOrl3dHqwKbTnLnU1cDi58K/38vp4rnJ7NOGxN/k2N3TntXW29bDuuvb1+ysmdLh0NrPmpOjkqOaj6W0jNfux5nR\",\n" +
                "                \"29Tny9Lg39Db3pqY5s7c29Kky93Y55iv0ezN4cvDsJyZmKuPZI+pkuLex9Xl\",\n" +
                "\t\t\t\t\"z+Ha3ZParoTh3Maj5NjQ7dSfxJzQ5dHSpN2PtdfDweLXzcrAptLT4aHP1+DUz9ar07vi1c3tmleQnp+jmo+rltDm0sTntIza38yj09fd2qfF0d/U1w==\",\n" +
                "\t\t\t\t\"2drZ08vV6crX4b67v6SL6MSojeHVod3E5tjP7NPP39vS1t7Iosac2ODay568z7Pcwt3i383DzKTLnN3U3sjm2Kbq3tHW3N2jnY9lj56fo6WC3+fJ19yamNWXxKiHY9vKy5w=\",\n" +
                "\t\t\t\t\"6NjjnsnPuaetjc3a7ZfU482bwNrQoeTI4KfLoN/N7+LZls/Nrsjendji0+LUxOary+G0jJirj2SPnqqW2dPY2NOtjbuj1ZeQqduQu5c=\",\n" +
                "\t\t\t\t\"6OOg5Mjg0qe4pYLk69CW9MSmzuLk5djA4uLW4M6N7+LZltTIpLTi2N+Y1N7jwtXVmpipmpiqj2Sakd7n0sTiroTOmLujoZjk1rtd\",\n" +
                "\t\t\t\t\"1uTn2c7c0qe4pYLY55fQ79Ojztqd1tnR1aHW5tPLo9PS2M/Tncuc5OHkyOCuhKKbj6WpmaOdzqjH0+Gujbuez4+amdvR1ZI=\",\n" +
                "\t\t\t\t\"29Tnz9Hj48nX3MSwnNLXqMyZ09Ph5trH1eXGoN3E5+/Sy9+NhMvj1tzYstXl19vNxKPc3tvuzqGs0+Pb2cOrlpGjm4+lqaSL6dOcxOCqlozE3ufT64w=\",\n" +
                "                \"3NDW2dLP6NHe2cDZtIzL6cxiwNrY1cvB0aHP083O6KfM1+zEYtTi2N/djcfYw8feyOHsl9fopZ3L08Tj1s7R15yVmpClqZmYtYKj09bU5aWCs7StvsbSoOza1OS7Ysjc4ufLy9zSy9Pcu52g0dzuz26OnQ==\",\n" +
                "                \"3NDW2dLP5cTXpYLk69CW28+VwtbUoc7E4tXaoM/X2qeRlqSIYsSfqpaaj6GjkaKlguTt0c3smleO3NDW2dKf6ZKhzdKk6NnbqcOZ0dDo\",\n" +
                "                \"4NTmz9Hm2NOtjcLk5pfi4sit1Jzl6NbN1eXC1NPL3u3ilt3OotPg3t/WxOKhs9fdxOfvztq9zqLT4N7f1sTiroSjm4+mqZqjnc6ox9Ph\"\n" +
                "            ],\n" +
                "            \"sqli_item\":{\n" +
                "                \"function\":\"dXBkYXRleG1sLGV4dHJhdnR2YWx1ZSxzbGVlcCxiZW5jaG1hcmsscGdfc2xlZXAsZ3JvdXBfY29uY2F0LGNvbmNhdF93cyxzdWJzdHIsc3Vic3RyaW5nLG1pZCxsZW5ndGgsb3JkLGFzY2lpLGhleCxiaW4scmFuZCxyaWdodCxsZWZ0LGNoYXIsY2hyLGNvbmNhdA==\",\n" +
                "                \"keyword\":\"c2VsZWN0LWZyb20saW5zZXJ0LWludG8sdXBkYXRlLXNldCxkZWxldGUtZnJvbSxjcmVhdGUtdGFibGUsaGFuZGxlci1vcGVuLHJlbmFtZS10YWJsZSx1bmlvbi1zZWxlY3QsYXR0YWNoLWRhdGFiYXNlLHNlbGVjdC1pbnRvLFdBSVRGT1ItREVMQVksYW5kLG9yLHhvcixub3QsZXhlYy14cF9jbWRzaGVsbA==\",\n" +
                "                \"symbol\":\"JiYsfHwsLS0sJyM=\"\n" +
                "            },\n" +
                "            \"throw\":[\n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "        \"sensitive\":{\n" +
                "            \"type\":[\n" +
                "                \"text/html\",\n" +
                "                \"application/json\"\n" +
                "            ],\n" +
                "            \"words\":[\n" +
                "\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"switchs\":{\n" +
                "        \"global\":{\n" +
                "            \"desc\": \"全局开关\",\n" +
                "            \"switch\":1\n" +
                "        },\n" +
                "        \"ban\":{\n" +
                "            \"file_write\":{\n" +
                "                \"switch\":0\n" +
                "            },\n" +
                "            \"wls_t3\":{\n" +
                "                \"switch\":2\n" +
                "            },\n" +
                "            \"wls_iiop\":{\n" +
                "                \"switch\":2\n" +
                "            }\n" +
                "        },\n" +
                "        \"behavior\": {\n" +
                "            \"command_exec\": {\n" +
                "                \"desc\": \"命令执行\",\n" +
                "                \"all\": {\n" +
                "                    \"desc\": \"1、禁止所有WEB请求执行命令\",\n" +
                "                    \"switch\": 2\n" +
                "                },\n" +
                "                \"reflect\": {\n" +
                "                    \"desc\": \"2、禁止通过反射执行命令\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"3、禁止通过反序列化执行命令\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"4、禁止通过表达式执行命令\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"any\": {\n" +
                "                    \"desc\": \"5、禁止通过请求参数执行命令\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"6、禁止通过 Webshell 执行命令\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"other\": {\n" +
                "                    \"desc\": \"7、禁止通过特定规则执行命令\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_read\": {\n" +
                "                \"desc\": \"任意文件读取\",\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"1、禁止通过反序列化读取文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"2、禁止通过表达式读取文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"any\": {\n" +
                "                    \"desc\": \"3、禁止通过请求参数读取文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"4、禁止通过 Webshell 读取文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"other\": {\n" +
                "                    \"desc\": \"5、禁止通过特定规则读取文件\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_write\": {\n" +
                "                \"desc\": \"任意文件写入\",\n" +
                "                \"reflect\": {\n" +
                "                    \"desc\": \"1、禁止通过反射写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"2、禁止通过反序列化写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"3、禁止通过表达式写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"any\": {\n" +
                "                    \"desc\": \"4、禁止通过请求参数写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"5、禁止通过 Webshell 写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"other\": {\n" +
                "                    \"desc\": \"6、禁止通过特定规则写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_read_write\": {\n" +
                "                \"desc\": \"任意文件读写\",\n" +
                "                \"reflect\": {\n" +
                "                    \"desc\": \"1、禁止通过反射写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"2、禁止通过反序列化读写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"3、禁止通过表达式读写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"any\": {\n" +
                "                    \"desc\": \"4、禁止通过请求参数读写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"5、禁止通过 Webshell 读写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"other\": {\n" +
                "                    \"desc\": \"6、禁止通过特定规则读写文件\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_upload\": {\n" +
                "                \"desc\": \"任意文件上传\",\n" +
                "                \"script\": {\n" +
                "                    \"desc\": \"1、禁止通过 multipart/form-data 方式上传脚本文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"war\":{\n" +
                "                    \"desc\": \"2、禁止通过Multipart 方式上传 war 文件\",\n" +
                "                    \"switch\":2\n" +
                "                }\n" +
                "            },\n" +
                "            \"jndi\": {\n" +
                "                \"desc\": \"JNDI注入防护\",\n" +
                "                \"rmi\": {\n" +
                "                    \"desc\": \"1、禁止通过JNDI发起 rmi 外连\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"ldap\": {\n" +
                "                    \"desc\": \"2、禁止通过JNDI发起 ldap 外连\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"dns\": {\n" +
                "                    \"desc\": \"3、禁止通过JNDI发起 dns 查询\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"iiop\": {\n" +
                "                    \"desc\": \"4、禁止通过JNDI发起 iiop 外连\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"dns\": {\n" +
                "                \"desc\": \"DNS 查询防护\",\n" +
                "                \"all\": {\n" +
                "                    \"desc\": \"1、禁止黑名单内的域名查询(如 dnslog.cn、ceye.io 等)\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"xxe\": {\n" +
                "                \"desc\": \"XXE外部实体加载\",\n" +
                "                \"all\": {\n" +
                "                    \"desc\": \"1、禁止外部实体加载\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"protocol\": {\n" +
                "                    \"desc\": \"2、禁止 ftp:// 等异常协议加载外部实体\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"3、禁止通过反序列化加载外部实体\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"connect\": {\n" +
                "                \"desc\": \"非法外连防护\",\n" +
                "                \"all\": {\n" +
                "                    \"desc\": \"1、禁止所有WEB请求发起外连\",\n" +
                "                    \"switch\": 2\n" +
                "                },\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"2、禁止通过反序列化发起外连\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"3、禁止通过表达式发起外连\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"4、禁止通过 Webshell 发起外连\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"other\": {\n" +
                "                    \"desc\": \"5、禁止通过特定规则发起外连\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"ssrf\": {\n" +
                "                \"desc\": \"SSRF 请求伪造防护\",\n" +
                "                \"http\": {\n" +
                "                    \"desc\": \"1、禁止伪造 http 请求\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"gopher\": {\n" +
                "                    \"desc\": \"2、禁止伪造 gopher 请求\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"ognl\": {\n" +
                "                \"desc\": \"ognl 表达式获取信息\",\n" +
                "                \"all\": {\n" +
                "                    \"desc\": \"1、禁止通过 ognl 获取信息\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"memshell\": {\n" +
                "                \"desc\": \"内存马注入防护\",\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"1、禁止通过反序列化注入内存马\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"2、禁止通过表达式注入内存马\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"3、禁止通过 Webshell 注入内存马\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"bind\":{\n" +
                "                    \"desc\": \"4、禁止通过绑定JNDI注入内存马\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            },\n" +
                "            \"jni\": {\n" +
                "                \"desc\": \"JNI 方式绕过 rasp\",\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"1、禁止通过 Webshell 加载共享库\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"sqli\": {\n" +
                "                \"desc\": \"SQL注入漏洞防护\",\n" +
                "                \"all\": {\n" +
                "                    \"desc\": \"1、禁止 SQL 注入漏洞利用\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"jrmp\": {\n" +
                "                \"desc\": \"JRMP 注入防护\",\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"1、禁止通过反序列化注入JRMP\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"2、禁止通过表达式注入JRMP\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"3、禁止通过 Webshell 注入JRMP\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"reflect\": {\n" +
                "                \"desc\": \"非法反射防护\",\n" +
                "                \"field\": {\n" +
                "                    \"desc\": \"1、禁止通过反射访问指定的字段\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"list_file\": {\n" +
                "                \"desc\": \"目录遍历防护\",\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"1、禁止通过反序列化遍历目录\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"2、禁止通过表达式遍历目录\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"any\":{\n" +
                "                    \"desc\": \"3、禁止通过请求参数遍历目录\",\n" +
                "                    \"switch\":0\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"4、禁止通过 Webshell 遍历目录\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_delete\": {\n" +
                "                \"desc\": \"任意文件删除\",\n" +
                "                \"deserialize\": {\n" +
                "                    \"desc\": \"1、禁止通过反序列化删除文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"expression\": {\n" +
                "                    \"desc\": \"2、禁止通过表达式删除文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"any\": {\n" +
                "                    \"desc\": \"3、禁止通过请求参数删除文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"webshell\": {\n" +
                "                    \"desc\": \"4、禁止通过 Webshell 删除文件\",\n" +
                "                    \"switch\": 1\n" +
                "                },\n" +
                "                \"other\": {\n" +
                "                    \"desc\": \"5、禁止通过特定规则删除文件\",\n" +
                "                    \"switch\": 1\n" +
                "                }\n" +
                "            },\n" +
                "            \"expression\":{\n" +
                "                \"desc\": \"表达式注入防护\",\n" +
                "                \"spel\":{\n" +
                "                    \"desc\": \"1、禁止任意 spring 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"jexl3\":{\n" +
                "                    \"desc\": \"2、禁止任意 jexl3 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"groovy\":{\n" +
                "                    \"desc\": \"3、禁止任意 groovy 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"ognl\":{\n" +
                "                    \"desc\": \"4、禁止任意 ognl 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"javax\":{\n" +
                "                    \"desc\": \"5、禁止任意 javaxEL 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"mvel\":{\n" +
                "                    \"desc\": \"6、禁止任意 mvel 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"script\":{\n" +
                "                    \"desc\": \"7、禁止任意 script 引擎注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"velocity\":{\n" +
                "                    \"desc\": \"8、禁止任意 velocity 模板引擎注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"freemarker\":{\n" +
                "                    \"desc\": \"9、禁止任意 freemarker 模板引擎注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"bcel\":{\n" +
                "                    \"desc\": \"10、禁止任意 BCEL 注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"jxpath\":{\n" +
                "                    \"desc\": \"11、禁止任意 JXPath 表达式注入行为\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_move\":{\n" +
                "                \"desc\": \"文件移动或重命名\",\n" +
                "                \"any\":{\n" +
                "                    \"desc\": \"1、禁止通过请求参数移动文件\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"other\":{\n" +
                "                    \"desc\": \"2、禁止通过其他方式移动文件\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            },\n" +
                "            \"file_copy\":{\n" +
                "                \"desc\": \"文件复制\",\n" +
                "                \"any\":{\n" +
                "                    \"desc\": \"1、禁止通过请求参数复制文件\",\n" +
                "                    \"switch\":1\n" +
                "                },\n" +
                "                \"other\":{\n" +
                "                    \"desc\": \"2、禁止通过其他方式复制文件\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            },\n" +
                "            \"thread\":{\n" +
                "                \"desc\": \"线程注入防护\",\n" +
                "                \"all\":{\n" +
                "                    \"desc\": \"1、禁止通过恶意WEB请求启动线程\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            },\n" +
                "            \"traversal\":{\n" +
                "                \"desc\": \"遍历文件防护\",\n" +
                "                \"all\":{\n" +
                "                    \"desc\": \"1、禁止通过遍历程序反序列化\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            },\n" +
                "            \"xpathi\":{\n" +
                "                \"desc\": \" XPATH 注入\",\n" +
                "                \"all\":{\n" +
                "                    \"desc\": \"1、禁止XPATH 注入\",\n" +
                "                    \"switch\":1\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        \"sensitive\":{\n" +
                "            \"switch\":0\n" +
                "        },\n" +
                "        \"mem_enhance\":{\n" +
                "            \"switch\":0\n" +
                "        },\n" +
                "        \"traffic\":{\n" +
                "            \"total\":{\n" +
                "                \"switch\":0\n" +
                "            },\n" +
                "            \"xss\":{\n" +
                "                \"switch\":0\n" +
                "            },\n" +
                "            \"csrf\":{\n" +
                "                \"switch\":0\n" +
                "            }\n" +
                "        },\n" +
                "        \"user_patch\":{\n" +
                "            \"switch\":0\n" +
                "        },\n" +
                "        \"api\":{\n" +
                "            \"switch\":1\n" +
                "        }\n" +
                "    },\n" +
                "    \"list\":{\n" +
                "        \"white\":{\n" +
                "            \"domain\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"command\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"webshell\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"url\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"ip\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"memshell\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"path\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"sqli\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"expression\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"xss\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            }\n" +
                "        },\n" +
                "        \"black\":{\n" +
                "            \"url\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"ip\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            },\n" +
                "            \"function\":{\n" +
                "                \"rules\":[\n" +
                "\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "\t\"alternative\":{\n" +
                "\t\t\"scan\":[\n" +
                "\n" +
                "\t\t],\n" +
                "\t\t\"user_patch\":[\n" +
                "\t\t\n" +
                "\t\t],\n" +
                "\t\t\"api_control\":[\n" +
                "\t\t\n" +
                "\t\t],\n" +
                "\t\t\"load\":[\n" +
                "\t\t\t\".alternative.processResourceFusing\",\n" +
                "\t\t\t\".alternative.mShellCanBeExploitedClassNames\",\n" +
                "\t\t\t\".alternative.sensitiveInformationLeakage\",\n" +
                "\t\t\t\".alternative.appendHTMLContent\",\n" +
                "\t\t\t\".alternative.weekPassword\"\n" +
                "\t\t],\n" +
                "\t\t\"weekPassword\":[\n" +
                "\t\t    \"dG9tY2F0\",\n" +
                "\t\t    \"YWRtaW4=\",\n" +
                "\t\t    \"cm9vdA==\",\n" +
                "\t\t    \"d2VibG9naWMx\",\n" +
                "\t\t    \"I15bMC05XSsk\"\n" +
                "\t\t],\n" +
                "\t\t\"processResourceFusing\":{\n" +
                "\t\t\t\"switch\":0,\n" +
                "\t\t\t\"cpuThreshold\":80,\n" +
                "\t\t\t\"memThreshold\":10240\n" +
                "\t\t},\n" +
                "\t\t\"mShellCanBeExploitedClassNames\":[\n" +
                "\t\t\t\"javax.servlet.Servlet\",\n" +
                "\t\t\t\"jakarta.servlet.Servlet\",\n" +
                "\t\t\t\"javax.servlet.Filter\",\n" +
                "\t\t\t\"jakarta.servlet.Filter\",\n" +
                "\t\t\t\"org.apache.catalina.Valve\",\n" +
                "\t\t\t\"javax.servlet.ServletRequestListener\",\n" +
                "\t\t\t\"jakarta.servlet.ServletRequestListener\",\n" +
                "\t\t\t\"com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet\",\n" +
                "\t\t\t\"javax.servlet.FilterChain\",\n" +
                "\t\t\t\"jakarta.servlet.FilterChain\",\n" +
                "\t\t\t\"jakarta.websocket.Endpoint\",\n" +
                "\t\t\t\"javax.websocket.Endpoint\"\n" +
                "\t\t],\n" +
                "\t\t\"sensitiveInformationLeakage\":{\n" +
                "\t\t\t\"switch\":1,\n" +
                "\t\t\t\"rules\":[\n" +
                "                        {\n" +
                "                            \"label\":\"id\",\n" +
                "                            \"rule\":\"I1xiWzEtOV1cZHs1fVsxLTJdXGR7M30oKDBcZCl8KDFbMC0yXSkpKChbMDEyXVxkKXwzWzAtMV0pXGR7M30oXGR8WHx4KVxi\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"email\",\n" +
                "                            \"rule\":\"I1xiW1x3LiUrLV0rQFtcdy4tXStcLlthLXpBLVpdezIsNn1cYg==\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"mobile\",\n" +
                "                            \"rule\":\"I1xiKDEzWzAtOV18MTRbMDE0NTY4NzldfDE1WzAtMzUtOV18MTZbMjU2N118MTdbMC04XXwxOFswLTldfDE5WzAtMzUtOV0pXGR7OH1cYg==\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"savingsCard\",\n" +
                "                            \"rule\":\"I1xiWzEtOV0oKFswLTldezE1LDE2fSl8KFswLTldezE4fSkpXGI=\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"creditCard\",\n" +
                "                            \"rule\":\"I1xiNjJbMC01XVxkezEzLDE2fVxi\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"address\",\n" +
                "                            \"rule\":\"I1xiKFtcdTRlMDAtXHU5ZmE1XSsoPzrnnIF85biCfOiHquayu+WMuikpezF9KFtcdTRlMDAtXHU5ZmE1XSsoPzrluIJ85Yy6fOWOv3zlt55855ufKSl7MCwxfShbXHU0ZTAwLVx1OWZhNV0rKD866KGX6YGTfOmVh3zkuaEpKXswLDF9KFtcdTRlMDAtXHU5ZmE1XSsoPzrlj7d85p2RfOekvikpezAsMX0oW1x1NGUwMC1cdTlmYTVdKyg/Oui3r3zooZd85be3KSl7MCwxfShbXHU0ZTAwLVx1OWZhNV0rKD865byEfOWPt+alvHzmoIt85Y2V5YWDKSl7MCwxfVxi\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"landline\",\n" +
                "                            \"rule\":\"I1xiKDAxMHwwMlxkfDBbMy05XVxkezJ9KS0/KFxkezYsOH0pXGI=\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"licensePlate\",\n" +
                "                            \"rule\":\"JTIzJTVDYiU1QiV1NEVBQyV1NkQyNSV1NkNBQSV1NkUxRCV1NTE4MCV1OEM2QiV1NEU5MSV1OEZCRCV1OUVEMSV1NkU1OCV1NzY5NiV1OUM4MSV1NjVCMCV1ODJDRiV1NkQ1OSV1OEQ2MyV1OTEwMiV1Njg0MiV1NzUxOCV1NjY0QiV1ODQ5OSV1OTY1NSV1NTQwOSV1OTVGRCV1OEQzNSV1N0NBNCV1OTc1MiV1ODVDRiV1NURERCV1NUI4MSV1NzQzQyV1NEY3RiV1OTg4NiU1RCU3QjElN0QlNUJBLVolNUQlN0IxJTdEJTVCQS1aMC05JTVEJTdCNCU3RCU1QkEtWjAtOSV1NjMwMiV1NUI2NiV1OEI2NiV1NkUyRiV1NkZCMyU1RCU3QjElN0QlNUNi\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"gps\",\n" +
                "                            \"rule\":\"I1xiXGR7MSwzfVwuXGR7Nn0sXGR7MSwzfVwuXGR7Nn1cYg==\",\n" +
                "                            \"switch\":1\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"label\":\"businessLicense\",\n" +
                "                            \"rule\":\"I1xiWzAtOUEtSEotTlBRUlRVV1hZXXsyfVxkezZ9WzAtOUEtSEotTlBRUlRVV1hZXXsxMH1cYg==\",\n" +
                "                            \"switch\":1\n" +
                "                        }\n" +
                "                    ]\n" +
                "\t\t},\n" +
                "\t\t\"appendHTMLContent\":\"\"\n" +
                "\t}\n" +
                "}";
        JSONObject parse = JSONObject.parse(oldRaspConfig);
        JSONObject jsonObject1 = parse.getJSONObject("switchs");
        JSONObject jsonObject2 = jsonObject1.getJSONObject("behavior");

        JSONObject oldBehavior = JSONObject.parse(oldRaspConfig).getJSONObject("switchs").getJSONObject("behavior");
        Behavior oldBehaviorObj = JSONObject.parseObject(oldBehavior.toJSONString(), Behavior.class);

        JSONObject newBehavior = JSONObject.parse(newRaspConfig).getJSONObject("switchs").getJSONObject("behavior");
        Behavior newBehaviorObj = JSONObject.parseObject(newBehavior.toJSONString(), Behavior.class);

        //command_exec
        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec())){
            if(ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getAll())){
                RaspSwitchsStatus all = oldBehaviorObj.getCommand_exec().getAll();
                newBehaviorObj.getCommand_exec().getAll().setSwitchStatus(all.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getCommand_exec().getAny();
                newBehaviorObj.getCommand_exec().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getCommand_exec().getDeserialize();
                newBehaviorObj.getCommand_exec().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getCommand_exec().getExpression();
                newBehaviorObj.getCommand_exec().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getCommand_exec().getOther();
                newBehaviorObj.getCommand_exec().getOther().setSwitchStatus(other.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getReflect())) {
                RaspSwitchsStatus reflect = oldBehaviorObj.getCommand_exec().getReflect();
                newBehaviorObj.getCommand_exec().getReflect().setSwitchStatus(reflect.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getCommand_exec().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getCommand_exec().getWebshell();
                newBehaviorObj.getCommand_exec().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getConnect())){
            if(ObjectUtils.isNotEmpty(oldBehaviorObj.getConnect().getAll())){
                RaspSwitchsStatus all = oldBehaviorObj.getConnect().getAll();
                newBehaviorObj.getConnect().getAll().setSwitchStatus(all.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getConnect().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getConnect().getDeserialize();
                newBehaviorObj.getConnect().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getConnect().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getConnect().getExpression();
                newBehaviorObj.getConnect().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getConnect().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getConnect().getOther();
                newBehaviorObj.getConnect().getOther().setSwitchStatus(other.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getConnect().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getConnect().getWebshell();
                newBehaviorObj.getConnect().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression())){
            if(ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getBcel())){
                RaspSwitchsStatus bcel = oldBehaviorObj.getExpression().getBcel();
                newBehaviorObj.getExpression().getBcel().setSwitchStatus(bcel.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getFreemarker())) {
                RaspSwitchsStatus freemarker = oldBehaviorObj.getExpression().getFreemarker();
                newBehaviorObj.getExpression().getFreemarker().setSwitchStatus(freemarker.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getGroovy())) {
                RaspSwitchsStatus groovy = oldBehaviorObj.getExpression().getGroovy();
                newBehaviorObj.getExpression().getGroovy().setSwitchStatus(groovy.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getJavax())) {
                RaspSwitchsStatus javax = oldBehaviorObj.getExpression().getJavax();
                newBehaviorObj.getExpression().getJavax().setSwitchStatus(javax.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getJexl3())) {
                RaspSwitchsStatus jexl3 = oldBehaviorObj.getExpression().getJexl3();
                newBehaviorObj.getExpression().getJexl3().setSwitchStatus(jexl3.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getJxpath())) {
                RaspSwitchsStatus jxpath = oldBehaviorObj.getExpression().getJxpath();
                newBehaviorObj.getExpression().getJxpath().setSwitchStatus(jxpath.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getMvel())) {
                RaspSwitchsStatus mvel = oldBehaviorObj.getExpression().getMvel();
                newBehaviorObj.getExpression().getMvel().setSwitchStatus(mvel.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getOgnl())) {
                RaspSwitchsStatus ognl = oldBehaviorObj.getExpression().getOgnl();
                newBehaviorObj.getExpression().getOgnl().setSwitchStatus(ognl.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getScript())) {
                RaspSwitchsStatus script = oldBehaviorObj.getExpression().getScript();
                newBehaviorObj.getExpression().getScript().setSwitchStatus(script.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getSpel())) {
                RaspSwitchsStatus spel = oldBehaviorObj.getExpression().getSpel();
                newBehaviorObj.getExpression().getSpel().setSwitchStatus(spel.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getExpression().getVelocity())) {
                RaspSwitchsStatus velocity = oldBehaviorObj.getExpression().getVelocity();
                newBehaviorObj.getExpression().getVelocity().setSwitchStatus(velocity.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_copy())){
            if(ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_copy().getAny())){
                RaspSwitchsStatus any = oldBehaviorObj.getFile_copy().getAny();
                newBehaviorObj.getFile_copy().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_copy().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getFile_copy().getOther();
                newBehaviorObj.getFile_copy().getOther().setSwitchStatus(other.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_delete())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_delete().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getFile_delete().getAny();
                newBehaviorObj.getFile_delete().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_delete().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getFile_delete().getDeserialize();
                newBehaviorObj.getFile_delete().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_delete().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getFile_delete().getExpression();
                newBehaviorObj.getFile_delete().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_delete().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getFile_delete().getOther();
                newBehaviorObj.getFile_delete().getOther().setSwitchStatus(other.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_delete().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getFile_delete().getWebshell();
                newBehaviorObj.getFile_delete().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_move())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_move().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getFile_move().getAny();
                newBehaviorObj.getFile_move().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_move().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getFile_move().getOther();
                newBehaviorObj.getFile_move().getOther().setSwitchStatus(other.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getFile_read().getAny();
                newBehaviorObj.getFile_read().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getFile_read().getDeserialize();
                newBehaviorObj.getFile_read().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getFile_read().getExpression();
                newBehaviorObj.getFile_read().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getFile_read().getOther();
                newBehaviorObj.getFile_read().getOther().setSwitchStatus(other.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getFile_read().getWebshell();
                newBehaviorObj.getFile_read().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read_write())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read_write().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getFile_read_write().getAny();
                newBehaviorObj.getFile_read_write().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read_write().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getFile_read_write().getDeserialize();
                newBehaviorObj.getFile_read_write().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read_write().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getFile_read_write().getExpression();
                newBehaviorObj.getFile_read_write().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read_write().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getFile_read_write().getOther();
                newBehaviorObj.getFile_read_write().getOther().setSwitchStatus(other.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_read_write().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getFile_read_write().getWebshell();
                newBehaviorObj.getFile_read_write().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_upload())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_upload().getWar())) {
                RaspSwitchsStatus war = oldBehaviorObj.getFile_upload().getWar();
                newBehaviorObj.getFile_upload().getWar().setSwitchStatus(war.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_upload().getScript())) {
                RaspSwitchsStatus script = oldBehaviorObj.getFile_upload().getScript();
                newBehaviorObj.getFile_upload().getScript().setSwitchStatus(script.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getFile_write().getAny();
                newBehaviorObj.getFile_write().getAny().setSwitchStatus(any.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getFile_write().getDeserialize();
                newBehaviorObj.getFile_write().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getFile_write().getExpression();
                newBehaviorObj.getFile_write().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write().getOther())) {
                RaspSwitchsStatus other = oldBehaviorObj.getFile_write().getOther();
                newBehaviorObj.getFile_write().getOther().setSwitchStatus(other.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getFile_write().getWebshell();
                newBehaviorObj.getFile_write().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getFile_write().getReflect())) {
                RaspSwitchsStatus reflect = oldBehaviorObj.getFile_write().getReflect();
                newBehaviorObj.getFile_write().getReflect().setSwitchStatus(reflect.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getJndi())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJndi().getDns())) {
                RaspSwitchsStatus dns = oldBehaviorObj.getJndi().getDns();
                newBehaviorObj.getJndi().getDns().setSwitchStatus(dns.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJndi().getIiop())) {
                RaspSwitchsStatus iiop = oldBehaviorObj.getJndi().getIiop();
                newBehaviorObj.getJndi().getIiop().setSwitchStatus(iiop.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJndi().getRmi())) {
                RaspSwitchsStatus rmi = oldBehaviorObj.getJndi().getRmi();
                newBehaviorObj.getJndi().getRmi().setSwitchStatus(rmi.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJndi().getLdap())) {
                RaspSwitchsStatus ldap = oldBehaviorObj.getJndi().getLdap();
                newBehaviorObj.getJndi().getLdap().setSwitchStatus(ldap.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getJni())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJni().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getJni().getWebshell();
                newBehaviorObj.getJni().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }

        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getJrmp())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJrmp().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getJrmp().getDeserialize();
                newBehaviorObj.getJrmp().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJrmp().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getJrmp().getExpression();
                newBehaviorObj.getJrmp().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getJrmp().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getJrmp().getWebshell();
                newBehaviorObj.getJrmp().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }

        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getList_file())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getList_file().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getList_file().getDeserialize();
                newBehaviorObj.getList_file().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getList_file().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getList_file().getExpression();
                newBehaviorObj.getList_file().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getList_file().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getList_file().getWebshell();
                newBehaviorObj.getList_file().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getList_file().getAny())) {
                RaspSwitchsStatus any = oldBehaviorObj.getList_file().getAny();
                newBehaviorObj.getList_file().getAny().setSwitchStatus(any.getSwitchStatus());
            }

        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getMemshell())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getMemshell().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getMemshell().getDeserialize();
                newBehaviorObj.getMemshell().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getMemshell().getExpression())) {
                RaspSwitchsStatus expression = oldBehaviorObj.getMemshell().getExpression();
                newBehaviorObj.getMemshell().getExpression().setSwitchStatus(expression.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getMemshell().getWebshell())) {
                RaspSwitchsStatus webshell = oldBehaviorObj.getMemshell().getWebshell();
                newBehaviorObj.getMemshell().getWebshell().setSwitchStatus(webshell.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getMemshell().getBind())) {
                RaspSwitchsStatus bind = oldBehaviorObj.getMemshell().getBind();
                newBehaviorObj.getMemshell().getBind().setSwitchStatus(bind.getSwitchStatus());
            }

        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getOgnl())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getOgnl().getAll())) {
                RaspSwitchsStatus all = oldBehaviorObj.getOgnl().getAll();
                newBehaviorObj.getOgnl().getAll().setSwitchStatus(all.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getReflect())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getReflect().getField())) {
                RaspSwitchsStatus field = oldBehaviorObj.getReflect().getField();
                newBehaviorObj.getReflect().getField().setSwitchStatus(field.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getSqli())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getSqli().getAll())) {
                RaspSwitchsStatus all = oldBehaviorObj.getSqli().getAll();
                newBehaviorObj.getSqli().getAll().setSwitchStatus(all.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getSsrf())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getSsrf().getHttp())) {
                RaspSwitchsStatus http = oldBehaviorObj.getSsrf().getHttp();
                newBehaviorObj.getSsrf().getHttp().setSwitchStatus(http.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getSsrf().getGopher())) {
                RaspSwitchsStatus gopher = oldBehaviorObj.getSsrf().getGopher();
                newBehaviorObj.getSsrf().getGopher().setSwitchStatus(gopher.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getThread())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getThread().getAll())) {
                RaspSwitchsStatus all = oldBehaviorObj.getThread().getAll();
                newBehaviorObj.getThread().getAll().setSwitchStatus(all.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getTraversal())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getTraversal().getAll())) {
                RaspSwitchsStatus all = oldBehaviorObj.getTraversal().getAll();
                newBehaviorObj.getTraversal().getAll().setSwitchStatus(all.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getXpathi())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getXpathi().getAll())) {
                RaspSwitchsStatus all = oldBehaviorObj.getXpathi().getAll();
                newBehaviorObj.getXpathi().getAll().setSwitchStatus(all.getSwitchStatus());
            }
        }

        if(ObjectUtils.isNotEmpty(oldBehaviorObj) && ObjectUtils.isNotEmpty(oldBehaviorObj.getXxe())){
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getXxe().getAll())) {
                RaspSwitchsStatus all = oldBehaviorObj.getXxe().getAll();
                newBehaviorObj.getXxe().getAll().setSwitchStatus(all.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getXxe().getDeserialize())) {
                RaspSwitchsStatus deserialize = oldBehaviorObj.getXxe().getDeserialize();
                newBehaviorObj.getXxe().getDeserialize().setSwitchStatus(deserialize.getSwitchStatus());
            }
            if (ObjectUtils.isNotEmpty(oldBehaviorObj.getXxe().getProtocol())) {
                RaspSwitchsStatus protocol = oldBehaviorObj.getXxe().getProtocol();
                newBehaviorObj.getXxe().getProtocol().setSwitchStatus(protocol.getSwitchStatus());
            }
        }

        String jsonString = JSON.toJSONString(newBehaviorObj);
        JSONObject newBehavior1 = JSONObject.parse(jsonString);
        System.out.println(newBehavior1);

        JSONObject switchs = JSONObject.parse(newRaspConfig).getJSONObject("switchs");
        switchs.put("behavior", newBehavior1);
        JSONObject parse1 = JSONObject.parse(newRaspConfig);
        parse1.put("switchs",switchs);
        System.out.println(parse1.toJSONString());


        com.alibaba.fastjson.JSONObject midViewRaspProtectItemVOJson = new com.alibaba.fastjson.JSONObject();// 回显给前端的防护项json
        midViewRaspProtectItemVOJson.put("midRaspProtectItemVOList", "sssss");
        midViewRaspProtectItemVOJson.put("midRaspProtectItemVOList", "ttttt");
        System.out.println(midViewRaspProtectItemVOJson);


        System.out.println("完成");
//        for (String key : newBehavior.keySet()) {
//            Object o = oldBehavior.get(key);
//            System.out.println(key + ": " + o);
//        }
//        System.out.println(jsonObject2);


        String oldRaspVersion = "1.2.0";
        String newRaspVersion = "1.6.0";


        int result = compareVersion(oldRaspVersion, newRaspVersion);
        if(result < 0){
            System.out.println("需要升级");
        }


    }


    public static int compareVersion(String version1, String version2) {
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        int length = Math.max(v1Parts.length, v2Parts.length);
        for (int i = 0; i < length; i++) {
            int v1 = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
            int v2 = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;

            if (v1 < v2) return -1;
            if (v1 > v2) return 1;
        }
        return 0;
    }
}
