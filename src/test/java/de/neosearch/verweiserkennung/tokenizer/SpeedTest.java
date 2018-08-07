package de.neosearch.verweiserkennung.tokenizer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.neosearch.verweiserkennung.TextAnalyzer;
import de.neosearch.verweiserkennung.tokenfilter.LowercaseWhitelistFilter;

public class SpeedTest {

	public SpeedTest() {
	}
	// 5.5s, 4.8s

	private final static StringBuffer TEXT_1 = new StringBuffer(
			"<div class=\"container\"><h3 style=\"text-align:center;font-size:1rem\" class=\"jsx-4127630647\">Bundesarbeitsgerichts</h3><p style=\"font-size:0.9rem;font-weight:300;text-align:center;padding-top:0\" class=\"jsx-4127630647\">Entscheidungsdatum:<!-- --> <b style=\"font-weight:600\" class=\"jsx-4127630647\">28.02.2018</b></p><hr class=\"jsx-4127630647\"><h1 style=\"font-size:1.3rem;font-weight:bold;padding-bottom:50px;text-align:center\" class=\"jsx-4127630647\">BAG<!-- --> <!-- -->28.02.2018<!-- --> - <!-- -->4 AZR 816/16</h1><h2 style=\"font-size:1rem;padding-bottom:10px;padding-top:0\">Eingruppierung - Geschäftsstellenverwalterin - Bundesgericht</h2><hr style=\"margin-bottom:50px\"><dl class=\"jsx-4127630647\"><dt>Gericht: </dt><dd><strong>Bundesarbeitsgerichts</strong></dd><dt>Entscheidungsdatum: </dt><dd><strong>28.02.2018</strong></dd><dt>Aktenzeichen: </dt><dd><strong>4 AZR 816/16</strong></dd><dt>ECLI: </dt><dd><strong>ECLI:DE:BAG:2018:280218.U.4AZR816.16.0</strong></dd><dt>Dokumenttyp: </dt><dd><strong>Urteil</strong></dd><dt>Vorinstanz: </dt><dd>vorgehend ArbG Leipzig, 24. März 2016, Az: 5 Ca 4247/15, Urteilvorgehend Sächsisches Landesarbeitsgericht, 2. November 2016, Az: 3 Sa 213/16, Urteil\n"
					+ "   <!-- --> </dd></dl><hr style=\"margin-bottom:40px\" class=\"jsx-4127630647\"><h3 style=\"font-size:1.2rem\">Leitsätze</h3><div>\n"
					+ "      <div>\n" + "         <dl class=\"RspDL\">\n" + "            <dt></dt>\n"
					+ "            <dd>\n"
					+ "               <p>Eine Eingruppierung nach der neuen Entgeltordnung des Bundes (TV EntgO Bund) erfolgt nur auf Antrag und auch nur für den Fall, dass sich daraus für den Beschäftigten eine höhere Entgeltgruppe ergibt. Die Korrektur einer schon nach der Vergütungsordnung des BAT-O fehlerhaften Eingruppierung erfolgt dagegen unter Zugrundelegung der bisherigen Tätigkeitsmerkmale.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "      </div>\n"
					+ "   </div><h3 style=\"font-size:1.2rem\">Tenor</h3><div>\n" + "      <div>\n"
					+ "         <dl class=\"RspDL\">\n" + "            <dt></dt>\n" + "            <dd>\n"
					+ "               <p></p>\n" + "            </dd>\n" + "         </dl>\n"
					+ "         <dl class=\"RspDL\">\n" + "            <dt></dt>\n" + "            <dd>\n"
					+ "               <p style=\"margin-left:54pt\">1. Auf die Revision der Beklagten wird das Urteil des Sächsischen Landesarbeitsgerichts vom 2.&nbsp;November 2016 -&nbsp;3&nbsp;Sa 213/16&nbsp;- insoweit teilweise aufgehoben, als das Landesarbeitsgericht festgestellt hat, dass die Beklagte verpflichtet ist, die Klägerin vom 1.&nbsp;Mai bis zum 31.&nbsp;Dezember 2014 nach der Entgeltgruppe&nbsp;9a TV&nbsp;EntgO Bund zu vergüten und die sich insoweit ergebenden Differenzbeträge ab dem auf den jeweiligen Fälligkeitstag folgenden Tag mit fünf Prozentpunkten über dem jeweiligen Basiszinssatz zu verzinsen. Im Übrigen wird die Revision der Beklagten zurückgewiesen.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n"
					+ "               <p style=\"margin-left:54pt\">2. Im Umfang der Aufhebung wird die Berufung der Klägerin gegen das Urteil des Arbeitsgerichts Leipzig vom 24.&nbsp;März 2016 -&nbsp;5&nbsp;Ca 4247/15&nbsp;- zurückgewiesen.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n"
					+ "               <p style=\"margin-left:54pt\">3. Von den Kosten der ersten und zweiten Instanz hat die Klägerin 1/5 und die Beklagte 4/5 zu tragen. Von den Kosten der Revision hat die Klägerin 1/3 und die Beklagte 2/3 zu tragen.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <p></p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "      </div>\n"
					+ "   </div><h3 style=\"font-size:1.2rem\" class=\"jsx-305751140\">Tatbestand</h3><div class=\"jsx-305751140\">\n"
					+ "      <div>\n" + "         <dl class=\"RspDL\">\n" + "            <dt></dt>\n"
					+ "            <dd>\n" + "               <p></p>\n" + "            </dd>\n" + "         </dl>\n"
					+ "         <dl class=\"RspDL\">\n" + "            <dt>\n"
					+ "               <a name=\"rd_1\">1</a>\n" + "            </dt>\n" + "            <dd>\n"
					+ "               <p>Die Parteien streiten über die zutreffende Eingruppierung der Klägerin.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_2\">2</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>Die Klägerin ist auf der Basis des schriftlichen Arbeitsvertrags vom 2.&nbsp;September 2002 bei der Beklagten als Geschäftsstellenverwalterin und Urkundsbeamtin der Geschäftsstelle am Bundesverwaltungsgericht beschäftigt. Nach dessen §&nbsp;2 bestimmt sich</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <table class=\"Rsp\">\n"
					+ "                  <tbody><tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">„Das Arbeitsverhältnis … nach dem Bundesangestelltentarifvertrag-Ost (BAT-O) und den diesen ergänzenden, ändernden oder ersetzenden Tarifverträgen in der für den Arbeitgeber geltenden Fassung. Außerdem finden die für den Arbeitgeber jeweils geltenden sonstigen Tarifverträge Anwendung. Ausgenommen ist der Beihilfe-Tarifvertrag vom 15.&nbsp;Juni 1959 i. d. F. des Ergänzungstarifvertrages vom 26.&nbsp;Mai 1964.“</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "               </tbody></table>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <p></p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_3\">3</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>Die Geschäftsstelle des Bundesverwaltungsgerichts gliedert sich in sechs Arbeitsgruppen, die jeweils von einer Beamtin oder einem Beamten des gehobenen Dienstes geleitet werden. Die Aufgaben der Geschäftsstelle sind in der Geschäftsstellenordnung für das Bundesverwaltungsgericht (GStO-BVerwG) festgelegt, in der es ua. heißt:</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <table class=\"Rsp\">\n"
					+ "                  <tbody><tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"4\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">„<strong>§&nbsp;5</strong>&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"4\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">\n"
					+ "                           <strong>Geschäfte des mittleren Dienstes</strong>\n"
					+ "                        </p>\n" + "                     </td>\n" + "                  </tr>\n"
					+ "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"4\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">…&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"3\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">Die Beschäftigten des mittleren Dienstes haben insbesondere folgende Aufgaben zu erledigen:</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">a)&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"2\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">\n"
					+ "                           <span style=\"text-decoration:underline\">Urkundsbeamte/r Geschäftsstellenverwalter/in</span>\n"
					+ "                        </p>\n" + "                     </td>\n" + "                  </tr>\n"
					+ "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">●&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">Führung der Register, Verzeichnisse usw. nach Maßgabe dieser Geschäftsstellenordnung und aufgrund von Entscheidungen über die Geschäftsverteilung in den einzelnen Senaten</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:left\">●&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">Erfassung und Pflege von Verfahrensdaten gemäß GO§A-Anwenderhandbuch, Verwaltung des Schriftguts, Aktenführung einschließlich Aktenrücksendung, Überwachung des Aktenumlaufs und Fristenkontrolle</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:left\">●&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:justify\">Erledigung des Schreibwerks</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "               <p>(1) Im Rahmen der Betreuung der Aktenvorgänge in der Senatsgeschäftsstelle fallen schwierige Tätigkeiten in rechtlich erheblichem Umfang an.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_41\">41</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>(a) Die Bearbeitung von Sachstandsanfragen mit Auskunftsersuchen sind nach der Protokollnotiz Nr.&nbsp;2&nbsp;Buchst.&nbsp;h Teil&nbsp;II Abschnitt&nbsp;T Unterabschnitt&nbsp;I der Anlage&nbsp;1a zum BAT-O, die Beglaubigung von gerichtlichen Schreiben und Erteilung von Bescheinigungen wie Rechtskraftzeugnisse ua. sind gemäß der Protokollnotiz Nr.&nbsp;2 Buchst.&nbsp;b Teil&nbsp;II Abschnitt&nbsp;T Unterabschnitt&nbsp;I der Anlage&nbsp;1a zum BAT-O schwierige Tätigkeiten im Sinne des Tarifmerkmals. Auch sind die Verteilung der neu eingegangenen Verfahren entsprechend dem Geschäftsverteilungsplan und die Feststellung des zuständigen Richters gemäß der Protokollnotiz Nr.&nbsp;2 Buchst.&nbsp;g Teil&nbsp;II Abschnitt&nbsp;T Unterabschnitt&nbsp;I der Anlage&nbsp;1a zum BAT-O schwierig im Sinne des Tätigkeitsmerkmals. Der Anteil dieser Tätigkeiten an der von der Klägerin auszuübenden Gesamttätigkeit beträgt damit insgesamt 9&nbsp;Prozentpunkte. Das macht bezogen auf den Arbeitsvorgang einen Anteil von 11,54&nbsp;% (9&nbsp;% von 78&nbsp;%) aus.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_42\">42</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>(b) Damit erreicht der Anteil der schwierigen Tätigkeiten innerhalb dieses Arbeitsvorgangs ein rechtserhebliches Ausmaß. Dabei kann dahinstehen, ob dieses überhaupt quantitativ bestimmt werden kann. Jedenfalls sind die schwierigen Tätigkeiten im Streitfall nicht von nur untergeordneter Bedeutung. Das Landesarbeitsgericht hat insoweit -&nbsp;bezogen auf den von ihm bestimmten Arbeitsvorgang „Aktenführung“&nbsp;- in revisionsrechtlich nicht zu beanstandender Weise angenommen, ohne die Arbeitsschritte der Durchführung von Beglaubigungen und der Bearbeitung von Sachstandsanfragen könne ein sinnvolles Arbeitsergebnis nicht erzielt werden <em>(vgl. dazu BAG 20.&nbsp;Oktober 1993 -&nbsp;4&nbsp;AZR 45/93&nbsp;- zu&nbsp;III&nbsp;3&nbsp;b&nbsp;bb der Gründe)</em>.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_43\">43</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>(2) Offenbleiben kann deshalb, welche Tätigkeiten die Klägerin im Zusammenhang mit der Kontrolle von Rechtsmittelfristen und der Führung des Eingangsregisters auszuüben hat, was zwischen den Parteien streitig ist. Insoweit kann vielmehr zugunsten der Beklagten unterstellt werden, dass diese der Klägerin übertragenen Tätigkeiten nicht schwierig im tariflichen Sinne sind.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_44\">44</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>(3) Auch bedarf es keiner Entscheidung darüber, ob die Aufgaben der Klägerin als Kostenbeamtin, die das Landesarbeitsgericht zu Recht gemäß der Protokollnotiz Nr.&nbsp;2 Buchst.&nbsp;e Teil&nbsp;II Abschnitt&nbsp;T Unterabschnitt&nbsp;I der Anlage&nbsp;1a zum BAT-O als schwierige Tätigkeit bewertet hat, sowie der Protokollführung in mündlichen Verhandlungen, die als solche dem Urkundsbeamten der Geschäftsstelle obliegen <em>(§&nbsp;105 VwGO iVm. §&nbsp;159 Abs.&nbsp;1 Satz&nbsp;2 ZPO)</em> und deshalb gemäß der Protokollnotiz Nr.&nbsp;2 Abs.&nbsp;2 Teil&nbsp;II Abschnitt&nbsp;T Unterabschnitt&nbsp;I der Anlage&nbsp;1a zum BAT-O ebenfalls eine schwierige Tätigkeit im Tarifsinne ist, im Rahmen eines einheitlichen großen Arbeitsvorgangs oder als gesonderte Arbeitsvorgänge zu berücksichtigen sind. Bei jedem insoweit denkbaren Zuschnitt erfüllen Arbeitsvorgänge im Umfang von mehr als der Hälfte der Gesamtarbeitszeit die Anforderungen des Tätigkeitsmerkmals.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_45\">45</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>dd) Da die Klägerin sich schließlich in der Zeit vom 1.&nbsp;September 2002 bis zum 31.&nbsp;August 2005 (und damit bereits vor Inkrafttreten des TVöD) in ihrer nach der VergGr.&nbsp;Vc Fallgr.&nbsp;1 der Anlage&nbsp;1a zum BAT-O zu bewertenden Tätigkeit bewährt hat, erfüllt sie auch die geforderte dreijährige Bewährung des Tätigkeitsmerkmals der VergGr.&nbsp;Vb BAT-O. Von einer solchen Bewährung gehen die Parteien übereinstimmend aus. Die Beklagte hat dies in der mündlichen Verhandlung vor dem Senat noch einmal ausdrücklich bestätigt.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_46\">46</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>4. Mit ihrem der Beklagten -&nbsp;unstreitig&nbsp;- am folgenden Tag zugegangenen Geltendmachungsschreiben vom 30.&nbsp;Juni 2015 hat die Klägerin die Ausschlussfrist des §&nbsp;37 Abs.&nbsp;1 TVöD hinsichtlich der Ansprüche ab dem Monat Januar 2015 gewahrt. Dagegen hat die Beklagte auch zu keinem Zeitpunkt Einwendungen erhoben.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_47\">47</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>II. Hinsichtlich der sich aus der Eingruppierungsfeststellungsklage für das Jahr 2014 ergebenden Zahlungsansprüche ist die Klage unbegründet. Insoweit sind die Ansprüche verfallen, weil die Klägerin die Ausschlussfrist des §&nbsp;37 Abs.&nbsp;1 TVöD nicht gewahrt hat.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_48\">48</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>1. Das Landesarbeitsgericht ist rechtsfehlerhaft davon ausgegangen, für die von der Klägerin im Wege der Feststellungsklage geltend gemachten Ansprüche fände die Ausschlussfrist des §&nbsp;26 Abs.&nbsp;1 TVÜ-Bund Anwendung. Das trifft jedoch schon deshalb nicht zu, weil die Voraussetzungen der Norm nicht gegeben sind. Daher kann dahinstehen, ob die Vorschrift -&nbsp;wie das Landesarbeitsgericht meint&nbsp;- generell eine Spezialregelung gegenüber der Ausschlussfrist des §&nbsp;37 Abs.&nbsp;1 TVöD darstellt <em>(so wohl auch Breier/Dassau/Kiefer/Lang/Langenbrinck TVöD Stand&nbsp;1/2018 B&nbsp;2.2 §&nbsp;26 TVÜ-Bund Erl.&nbsp;2 Rn.&nbsp;6; Litschen in Adam/Bauer/Bettenhausen/ua. Tarifrecht der Beschäftigten im öffentlichen Dienst Stand Dezember 2015 Teil&nbsp;II §&nbsp;26 TVÜ-Bund&nbsp;B&nbsp;I Rn.&nbsp;4; Clemens/Scheuring/Steingen/Wiese TVöD Stand Januar 2018 Teil&nbsp;IV/3 Rn.&nbsp;372)</em>, oder ob sich diese lediglich auf den Wechsel in das neue tarifliche Entgeltsystem bezieht und es hinsichtlich der sich aus der Ausübung des Antragsrechts folgenden Zahlungsansprüche bei der allgemeinen Ausschlussfrist des §&nbsp;37 Abs.&nbsp;1 TVöD verbleibt <em>(so für §&nbsp;29a Abs.&nbsp;4 Satz&nbsp;1 TVÜ-Länder BeckOK TV-L/Dannenberg Stand 1.&nbsp;Januar 2013 TVÜ-Länder §&nbsp;29a Rn.&nbsp;38; Augustin ZTR&nbsp;2012, 484)</em> und wann diese ggf. fällig werden.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_49\">49</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>2. Nach §&nbsp;37 Abs.&nbsp;1 TVöD verfallen Ansprüche aus dem Arbeitsverhältnis, wenn sie nicht innerhalb einer Ausschlussfrist von sechs Monaten nach Fälligkeit vom Angestellten schriftlich geltend gemacht werden, soweit tarifvertraglich nichts anderes bestimmt ist. Nach §&nbsp;37 Abs.&nbsp;1 Satz&nbsp;2 TVöD reicht für denselben Sachverhalt die einmalige Geltendmachung des Anspruchs auch für später fällig werdende Leistungen aus.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_50\">50</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>a) Tarifliche Ausschlussfristen dienen der Rechtssicherheit und Rechtsklarheit. Der Anspruchsgegner soll sich auf die nach Auffassung des Anspruchstellers noch offene Forderung rechtzeitig einstellen, Beweise sichern und ggf. Rücklagen bilden können. Er soll vor der Verfolgung von Ansprüchen, mit deren Geltendmachung er nicht rechnet und nicht rechnen muss, geschützt werden <em>(BAG 22.&nbsp;September 2016 -&nbsp;6&nbsp;AZR 432/15&nbsp;- Rn.&nbsp;13)</em>.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_51\">51</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>b) Ausgehend von diesem Zweck ist die Ausschlussfrist nur gewahrt, wenn der Anspruchsteller unmissverständlich zum Ausdruck bringt, dass er Inhaber einer nach Grund und Höhe spezifizierten Forderung ist und auf der Erfüllung dieser Forderung besteht <em>(BAG 15.&nbsp;Dezember 2016 -&nbsp;6&nbsp;AZR 578/15&nbsp;- Rn.&nbsp;26)</em>. Das ist dann nicht der Fall, wenn der Arbeitnehmer lediglich „um Prüfung“ bittet, ob die Voraussetzungen eines näher bezeichneten Anspruchs vorliegen, weil er damit nicht zum Ausdruck bringt, den Arbeitgeber auch unabhängig vom Ergebnis der Prüfung auf Zahlung von Vergütung nach einer bestimmten Entgeltgruppe in Anspruch nehmen zu wollen <em>(vgl. zu §&nbsp;70 BAT BAG 10.&nbsp;Dezember 1997 -&nbsp;4&nbsp;AZR 228/96&nbsp;- zu&nbsp;II&nbsp;6 der Gründe)</em>.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_52\">52</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>3. Gemessen an diesen Maßstäben hat die Klägerin die Ansprüche für das Jahr 2014 nicht fristwahrend geltend gemacht.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_53\">53</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>a) Mit Schreiben vom 26.&nbsp;November 2014 hat sie lediglich um Überprüfung ihrer Eingruppierung in die Entgeltgruppe&nbsp;6 gebeten und die Protokollerklärung Nr.&nbsp;1 Buchst.&nbsp;e zu Teil&nbsp;III Nr.&nbsp;20 der Anlage&nbsp;1 zum TV&nbsp;EntgO&nbsp;Bund zitiert. Abgesehen davon, dass sie nicht zum Ausdruck gebracht hat, die Beklagte auch unabhängig vom Ergebnis der Prüfung auf Zahlung von Vergütung in Anspruch nehmen zu wollen, ist dem Schreiben auch nicht zu entnehmen, welche Entgeltgruppe die Klägerin für sich in Anspruch nehmen wollte. Sie hat weder ausdrücklich die begehrte Entgeltgruppe genannt noch hat sie durch die zitierte Protokollerklärung zum Ausdruck gebracht, nach welcher Entgeltgruppe sie meint, vergütet werden zu müssen. Auf die Protokollerklärung Nr.&nbsp;1 wird sowohl in der Entgeltgruppe&nbsp;6 als auch in der Entgeltgruppe&nbsp;8 als auch in der Entgeltgruppe&nbsp;9a TV&nbsp;EntgO&nbsp;Bund verwiesen. Der entscheidende Unterschied zwischen den Entgeltgruppen ist der Umfang der schwierigen Tätigkeiten.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_54\">54</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>b) Das Geltendmachungsschreiben vom 30.&nbsp;Juni 2015, welches der Beklagten erst am 1.&nbsp;Juli 2015 zugegangen ist, vermochte die Ausschlussfrist für Ansprüche betreffend die Monate Mai bis Dezember 2014 nicht zu wahren. Diese wurden gemäß §&nbsp;24 Abs.&nbsp;1 Satz&nbsp;2 TVöD jeweils am Monatsletzten fällig, mithin für Dezember 2014 am 31.&nbsp;Dezember 2014. Der für die schriftliche Geltendmachung vorgesehene Sechs-Monats-Zeitraum endete folglich mit Ablauf des 30.&nbsp;Juni 2015 <em>(</em>\n"
					+ "                  <em>§&nbsp;187 </em>\n" + "                  <em>Abs.&nbsp;1 iVm. </em>\n"
					+ "                  <em>§&nbsp;188 Abs.&nbsp;2 BGB</em>\n" + "                  <em>)</em>.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt>\n" + "               <a name=\"rd_55\">55</a>\n" + "            </dt>\n"
					+ "            <dd>\n"
					+ "               <p>III. Die Kosten waren gemäß §&nbsp;92 Abs.&nbsp;1 ZPO zwischen den Parteien entsprechend ihrem Obsiegen und Unterliegen verhältnismäßig zu teilen. Die weiter gehende Kostentragungspflicht der Klägerin in der Revisionsinstanz beruht auf §§&nbsp;565, 516 Abs.&nbsp;3 Satz&nbsp;1 ZPO, da sie ihre -&nbsp;klageerweiternde&nbsp;- Anschlussrevision zurückgenommen hat.</p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <p></p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <p></p>\n"
					+ "            </dd>\n" + "         </dl>\n" + "         <dl class=\"RspDL\">\n"
					+ "            <dt></dt>\n" + "            <dd>\n" + "               <table class=\"Rsp\">\n"
					+ "                  <tbody><tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"2\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;Eylert&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;Klose &nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;Rinck &nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n" + "                  </tr>\n" + "                  <tr>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"2\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;Edda Redeker&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">\n"
					+ "                        <p style=\"text-align:center\">&nbsp;&nbsp;&nbsp;&nbsp;Bredendiek &nbsp;&nbsp;&nbsp;&nbsp;</p>\n"
					+ "                     </td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                     <td colspan=\"1\" rowspan=\"1\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
					+ "                  </tr>\n" + "               </tbody></table>\n" + "            </dd>\n"
					+ "         </dl>\n" + "         <dl class=\"RspDL\">\n" + "            <dt></dt>\n"
					+ "            <dd>\n" + "               <p></p>\n" + "            </dd>\n" + "         </dl>\n"
					+ "         <dl class=\"RspDL\">\n" + "            <dt></dt>\n" + "            <dd>\n"
					+ "               <p></p>\n" + "            </dd>\n" + "         </dl>\n" + "      </div>\n"
					+ "   </div></div>");

	private final static StringBuffer TEXT = TEXT_1.append(TEXT_1).append(TEXT_1).append(TEXT_1);
//	private final static StringBuffer TEXT = TEXT_1;
	private static LowercaseWhitelistFilter lowerFilter = new LowercaseWhitelistFilter("testToken",
			Arrays.asList("Test A"), Arrays.asList("/test"));;
	private static LowercaseWhitelistFilter sectionFilter = new LowercaseWhitelistFilter("sectionToken",
			Arrays.asList("BGB §A"), Arrays.asList("/bgb/a"));
	private static LowercaseWhitelistFilter aktenzeichenFilter = new LowercaseWhitelistFilter("aktenzeichen",
			Arrays.asList("VII B 28/11"), Arrays.asList("vii-b-28/11"));
	private static TextAnalyzer textAnalyzer = TextAnalyzer.createTextAnalyzer(25).add(lowerFilter).add(sectionFilter)
			.add(aktenzeichenFilter).build();
	private static TextAnalyzer textAnalyzerNew = TextAnalyzer.createTextAnalyzer(12).add(lowerFilter)
			.add(sectionFilter).add(aktenzeichenFilter).build();

	@Test
	public static void testStreamSpeed() {
		System.out.println("NEW:" + "stream");

		long firstCurrentTimeInMillis = System.currentTimeMillis();
		long currentTimeMillis = System.currentTimeMillis();
		List<Token> tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();
		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		currentTimeMillis = System.currentTimeMillis();
		tokens = textAnalyzerNew.analyze(TEXT.toString()).getTokens();

		System.out.println("NEW:" + (System.currentTimeMillis() - currentTimeMillis));
		System.out.println("NEW result:" + (System.currentTimeMillis() - firstCurrentTimeInMillis));
		System.out.println(tokens.size());
	}

	public static void main(String[] args) {
		testStreamSpeed();
//		testStreamSpeed();
	}

}